import 'package:flutter/material.dart';
import 'package:flutter_dm/api/api_manager.dart';
import 'package:flutter_dm/constant/empty_view_status.dart';
import 'package:flutter_dm/model/gank_data_entity.dart';
import 'package:flutter_dm/views/image_item.dart';
import 'package:flutter_dm/widget/load_more_view.dart';
import 'package:flutter_dm/widget/smart_listview.dart';

///图片页面
class ImagePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => ImagePageState();
}

class ImagePageState extends State<ImagePage>
    with AutomaticKeepAliveClientMixin {
  ///默认Loading
  EmptyViewStatus _emptyViewStatus = EmptyViewStatus.loading;

  ///页数
  int _pageNo = 1;

  ///是否正在加载中
  bool _isLoadMore = false;

  ///是否还有更多
  bool _hasMore = false;

  ///数据
  List<GankdataEntity> _data = [];

  ///滚动监听器
  ScrollController _scrollController;

  @override
  void initState() {
    super.initState();
    _initController();
    _loadData();
  }

  ///初始化滑动控制器
  void _initController() {
    _scrollController = new ScrollController();
    _scrollController.addListener(() {
      if (_scrollController.position.pixels ==
          _scrollController.position.maxScrollExtent) {
        _onLoadMore();
      }
    });
  }

  ///加载数据
  Future<void> _loadData() async {
    _isLoadMore ? _pageNo++ : _pageNo = 1;
    await ApiManager().getCategoryData(_pageNo).then((resultList) {
      setState(() {
        _data.addAll(resultList);
        _hasMore = resultList.isNotEmpty;
        _emptyViewStatus = _pageNo == 1 && _data.isEmpty
            ? EmptyViewStatus.noData
            : EmptyViewStatus.hasData;
      });
    });
  }

  ///刷新
  Future<void> _onRefresh() async {
    _isLoadMore = false;
    _data.clear();
    await _loadData();
    return null;
  }

  ///开始加载更多
  Future<void> _onLoadMore() async {
    _isLoadMore = true;
    _loadData();
  }

  ///设置数据
  Widget _renderList(int index) {
    if (index == _data.length) {
      return new LoadMoreView(_hasMore);
    }
    return new ImageListItem(_data[index].url, currentIndex: index);
  }

  @override
  Widget build(BuildContext context) {
    return new SmartListView(
        data: this._data,
        emptyViewStatus: this._emptyViewStatus,
        backgroundColor: Colors.white,
        renderList: (index) => this._renderList(index),
        onRefresh: () async => this._onRefresh(),
        onLoadMore: () async => this._onLoadMore());
  }

  @override
  bool get wantKeepAlive => true;

  @override
  void dispose() {
    super.dispose();
    _scrollController.dispose();
  }
}
