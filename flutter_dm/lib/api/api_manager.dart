import 'package:dio/dio.dart';
import 'package:flutter_dm/res/strings.dart';
import 'api_config.dart';
import 'package:flutter_dm/model/gank_basic_entity.dart';
import 'package:flutter_dm/utils/toast_utils.dart';
import 'package:flutter_dm/model/gank_data_entity.dart';

class ApiManager {
  Dio _dio;

  //工厂构造函数(Factory constructors)单例模式
  factory ApiManager() => _getInstance();
  static ApiManager _instance;

  ApiManager._internal() {
    var options = BaseOptions(
        baseUrl: Api.DATA, connectTimeout: 10000, receiveTimeout: 3000);
    _dio = Dio(options);
  }

  //获取ApiManger对象
  static ApiManager _getInstance() {
    if (_instance == null) {
      _instance = new ApiManager._internal();
    }
    return _instance;
  }

  static ApiManager get instance => _getInstance();

  ///获取图片资源
  Future<List<GankdataEntity>> getCategoryData(int page, {count = 10}) async {
    List<GankdataEntity> resultList = [];
    Response response = await _dio.get('福利/$count/$page');
    try {
      GankBasicEntity basicEntity = GankBasicEntity.fromJson(response.data);
      resultList.addAll(basicEntity.results);
    } catch (e) {
      ToastUtils.showToast(StringValues.EMPTY_NO_DATA_ERROR);
    }
    return resultList;
  }
}
