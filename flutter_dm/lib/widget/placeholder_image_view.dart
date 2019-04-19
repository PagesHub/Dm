import 'package:flutter/material.dart';
import 'package:flutter_dm/res/colors.dart';
import 'package:transparent_image/transparent_image.dart';

class PlaceholderImageView extends StatelessWidget {
  final String imageUrl;
  final double height;
  final double width;
  final BoxFit fit;

  PlaceholderImageView(this.imageUrl,
      {Key key, this.height, this.width, this.fit = BoxFit.cover})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return new Stack(
      children: <Widget>[
        new Container(
            color: const Color(ColorValues.IMAGE_BACKGROUND_COLOR),
            width: width,
            height: height),
        new FadeInImage.memoryNetwork(
            image: imageUrl,
            placeholder: kTransparentImage,
            height: height,
            width: width,
            fit: fit)
      ],
    );
  }
}
