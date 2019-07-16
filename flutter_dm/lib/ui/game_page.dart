import 'package:flutter/material.dart';

import 'game/TeTris.dart';

class GamePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => HomePageState();
}

class HomePageState extends State<GamePage>
    with SingleTickerProviderStateMixin {
  @override
  Widget build(BuildContext context) {
    return GridView.count(
      crossAxisCount: 1,
      childAspectRatio:2,
      children: <Widget>[
        new Card(
          elevation: 10,
          child: MaterialButton(
              height: 150,
              color: Colors.amber,
              child: Text('俄罗斯方块'),
              onPressed: () {
                playGame();
              }),
        ),
      ],
    );
  }

  // 跳转有限界面
  void playGame() {
    Navigator.push(
      context,
      new MaterialPageRoute(builder: (context) => new TeTris()),
    );
  }
}
