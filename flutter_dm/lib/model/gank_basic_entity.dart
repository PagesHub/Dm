import 'package:flutter_dm/model/gank_data_entity.dart';

class GankBasicEntity {
  bool error;
  List<GankdataEntity> results;

  GankBasicEntity({this.error, this.results});

  GankBasicEntity.fromJson(Map<String, dynamic> json) {
    error = json['error'];
    if (json['results'] != null) {
      results = new List<GankdataEntity>();
      (json['results'] as List).forEach((v) {
        results.add(new GankdataEntity.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['error'] = this.error;
    if (this.results != null) {
      data['results'] = this.results.map((v) => v.toJson()).toList();
    }
    return data;
  }

  @override
  String toString() {
    return super.toString();
  }
}
