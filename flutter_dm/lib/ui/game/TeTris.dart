import 'package:flutter/material.dart';

class TeTris extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => TeTrisState();
}

class TeTrisState extends State<TeTris> with SingleTickerProviderStateMixin {
  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      child: Scaffold(
        body: const Center(
          child: Text('TeTris'),
        ),
      ), onWillPop: () {print("123784784584584"); },
    );
  }
}

