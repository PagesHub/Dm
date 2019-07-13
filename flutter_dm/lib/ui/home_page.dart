import 'package:flutter/material.dart';
import 'package:flutter_dm/widget/ln_appbar.dart';

///首页
class HomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => HomePageState();
}

class HomePageState extends State<HomePage> with AutomaticKeepAliveClientMixin {
  @override
  bool get wantKeepAlive => true;

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    //注意：一个app只能有一个MaterialApp
    return MaterialApp(
      debugShowCheckedModeBanner: false, // 设置这一属性即可
      //这个是Material library提供的一个widget，它提供了默认的导航栏、标题栏包含主屏幕的widget树的body属性
      home: new Scaffold(
        appBar: new LnAppBar(
          child: const Center(
            child: const Text('Flutter主页面'),
          ),
        ),
        body: const Center(
          child: const Text('Hello World'),
        ),
      ),
    );
  }
}
