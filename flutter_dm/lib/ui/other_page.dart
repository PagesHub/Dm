import 'package:flutter/material.dart';

///其他
class OtherPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return OtherPageState();
  }
}

class OtherPageState extends State<OtherPage>
    with SingleTickerProviderStateMixin {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Text('其他页面'),
      ),
    );
  }
}
