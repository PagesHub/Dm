import 'package:flutter/material.dart';
import 'package:flutter_dm/utils/flutter_toast.dart';

class ToastUtils {
  static showToast(String msg) {
    FlutterToast.showToast(
        msg: msg,
        toastLength: Toast.LENGTH_SHORT,
        gravity: ToastGravity.CENTER,
        timeInSecForIos: 1,
        backgroundColor: Colors.black12,
        textColor: Colors.white,
        fontSize: 16.0);
  }
}
