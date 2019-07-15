import 'package:flutter/material.dart';

class TeTris extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => TeTrisState();
}

class TeTrisState extends State<TeTris> with SingleTickerProviderStateMixin {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: const Center(
        child: Text('TeTris'),
      ),
    );
  }
}
