import 'package:flutter/material.dart';
import 'package:flutter_dm/constant/empty_view_status.dart';
import 'package:flutter_dm/res/strings.dart';
import 'package:flutter_dm/widget/empty_view.dart';

/// 缺省、下拉刷新、上拉加载封
class SmartListView extends StatefulWidget {
  /// 数据列表
  final List<Object> data;

  /// 背景色
  final Color backgroundColor;

  /// 缺省View状态
  final EmptyViewStatus emptyViewStatus;

  /// 缺省View文案显示
  final String emptyViewRemark;

  /// 是否启用下拉刷新
  final bool refreshEnable;

  /// 是否启用加载更多
  final bool loadMoreEnable;

  /// 渲染回调
  final Function(int) renderList;

  /// 刷新回调
  final Future<void> Function() onRefresh;

  /// 加载更多回调
  final Function onLoadMore;

  SmartListView({
    @required this.data,
    @required this.emptyViewStatus,
    @required this.renderList,
    this.emptyViewRemark = StringValues.EMPTY_NO_DATA_REMARK,
    this.backgroundColor,
    this.refreshEnable = true,
    this.loadMoreEnable = true,
    this.onRefresh,
    this.onLoadMore,
  })  : assert(data != null),
        assert(emptyViewStatus != null),
        assert(renderList != null);

  @override
  State<StatefulWidget> createState() => SmartListViewState();
}

class SmartListViewState extends State<SmartListView> {
  ScrollController _scrollController;

  @override
  void initState() {
    super.initState();
    _initController();
  }

  void _initController() {
    _scrollController = new ScrollController();
    // 启用加载更多,设置监听
    if (widget.refreshEnable) {
      _scrollController.addListener(() {
        if (_scrollController.position.pixels ==
            _scrollController.position.maxScrollExtent) {
          widget.onLoadMore();
        }
      });
    }
  }

  Widget _buildListView() => ListView.builder(
        controller: _scrollController,
        itemCount:
            widget.loadMoreEnable ? widget.data.length + 1 : widget.data.length,
        itemBuilder: (context, index) => widget.renderList(index),
      );

  @override
  Widget build(BuildContext context) {
    return new EmptyView(
      status: widget.emptyViewStatus,
      remark: widget.emptyViewRemark,
      child: new Container(
          color: widget.backgroundColor ?? Theme.of(context).backgroundColor,
          child: widget.loadMoreEnable
              ? new RefreshIndicator(
                  child: _buildListView(), onRefresh: widget.onRefresh)
              : _buildListView()),
    );
  }
}
