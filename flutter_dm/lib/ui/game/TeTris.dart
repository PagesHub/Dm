import 'package:flutter/material.dart';

class TeTris extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => TeTrisState();
}

class TeTrisState extends State<TeTris>  {
  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      child: Scaffold(
        body: const Center(
          child: Text('TeTris'),
        ),
      ),
      onWillPop: () {
        print("返回键点击了");Navigator.pop(context);
        return Future.value(false);
      },
    );
  }
}
