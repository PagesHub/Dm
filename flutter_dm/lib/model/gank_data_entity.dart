class GankdataEntity {
  String createdat;
  String publishedat;
  String sId;
  String source;
  bool used;
  String type;
  String url;
  String desc;
  String who;

  GankdataEntity(
      {this.createdat,
      this.publishedat,
      this.sId,
      this.source,
      this.used,
      this.type,
      this.url,
      this.desc,
      this.who});

  GankdataEntity.fromJson(Map<String, dynamic> json) {
    createdat = json['createdAt'];
    publishedat = json['publishedAt'];
    sId = json['_id'];
    source = json['source'];
    used = json['used'];
    type = json['type'];
    url = json['url'];
    desc = json['desc'];
    who = json['who'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['createdAt'] = this.createdat;
    data['publishedAt'] = this.publishedat;
    data['_id'] = this.sId;
    data['source'] = this.source;
    data['used'] = this.used;
    data['type'] = this.type;
    data['url'] = this.url;
    data['desc'] = this.desc;
    data['who'] = this.who;
    return data;
  }

  @override
  String toString() {
    return super.toString();
  }
}
