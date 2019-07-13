import 'package:flutter/material.dart';

class GamePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => HomePageState();
}

class HomePageState extends State<GamePage>
    with SingleTickerProviderStateMixin {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      body: ListView(
        children: <Widget>[
          Card(
            color: Colors.blue,
            child: Text("11111"),
          ),
        ],
      ),
    );
  }
}
