import 'package:flutter/material.dart';
import 'package:flutter_dm/constant/empty_view_status.dart';
import 'package:flutter_dm/res/strings.dart';

class EmptyView extends StatelessWidget {
  final Widget child;
  final String image;
  final String remark;
  final EmptyViewStatus status;

  EmptyView(
      {Key key,
      @required this.child,
      @required this.status,
      this.image,
      this.remark = StringValues.EMPTY_NO_DATA_REMARK})
      : super(key: key);

  Widget _buildLoadingView() =>
      new Center(child: const CircularProgressIndicator());

  Widget _buildNoDataView() => new Center(
        child: new Column(
          mainAxisSize: MainAxisSize.min,
          children: <Widget>[
            new Text(
              this.remark,
              textAlign: TextAlign.center,
              style:
                  const TextStyle(fontSize: 18.0, fontWeight: FontWeight.bold),
            )
          ],
        ),
      );

  @override
  Widget build(BuildContext context) {
    switch (this.status) {
      case EmptyViewStatus.loading:
        // Loading
        return _buildLoadingView();
      case EmptyViewStatus.hasData:
        // Child
        return this.child;
      case EmptyViewStatus.noData:
        // NoData
        return _buildNoDataView();
    }
    return _buildLoadingView();
  }
}
