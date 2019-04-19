import 'package:flutter/material.dart';

///自定义appbar
class LnAppBar extends StatefulWidget implements PreferredSizeWidget {
  LnAppBar({@required this.child}) : assert(child != null);
  final Widget child;

  @override
  Size get preferredSize {
    return new Size.fromHeight(45.0);
  }

  @override
  State<StatefulWidget> createState() {
    return new LnAppBarState();
  }
}

class LnAppBarState extends State<LnAppBar> {
  @override
  Widget build(BuildContext context) {
    return widget.child;
  }
}
