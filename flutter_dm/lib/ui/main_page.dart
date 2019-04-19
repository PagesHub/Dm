import 'package:flutter/material.dart';
import 'package:flutter_dm/ui/home_page.dart';
import 'package:flutter_dm/ui/other_page.dart';
import 'package:flutter_dm/ui/image_page.dart';

class App extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new AppState();
  }
}

class AppState extends State<App> with TickerProviderStateMixin {
  var _pageChose;
  int _tabIndex = 0;

  @override
  void initState() {
    super.initState();
    _pageChose = new PageController(initialPage: 0, keepPage: true);
  }

  @override
  void dispose() {
    _pageChose..dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false, // 设置这一属性即可
      home: Scaffold(
        body: PageView(
          controller: _pageChose,
          physics: NeverScrollableScrollPhysics(),
          children: <Widget>[
            HomePage(),
            OtherPage(),
            ImagePage(),
          ],
        ),
        bottomNavigationBar: BottomNavigationBar(
          currentIndex: _tabIndex,
          type: BottomNavigationBarType.fixed,
          fixedColor: Colors.blue,
          onTap: (index) => _tab(index),
          items: [
            BottomNavigationBarItem(
              title: Text('首页'),
              icon: Icon(Icons.home),
            ),
            BottomNavigationBarItem(
              title: Text('其他'),
              icon: Icon(Icons.add),
            ),
            BottomNavigationBarItem(
              title: Text('美图'),
              icon: Icon(Icons.image),
            ),
          ],
        ),
      ),
    );
  }

  _tab(int index) {
    setState(() {
      _tabIndex = index;
      _pageChose.jumpToPage(index);
    });
  }
}
