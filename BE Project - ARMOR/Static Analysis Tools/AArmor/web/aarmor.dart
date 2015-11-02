import 'dart:html';
ButtonElement genButton;

void main() {
  querySelector("#sign_in").onClick.listen(reverseText);
  //querySelector("image_frame").
  
}

void reverseText(MouseEvent event) {
  /*var text = querySelector("#sample_text_id").text;
  var buffer = new StringBuffer();
  for (int i = text.length - 1; i >= 0; i--) {
    buffer.write(text[i]);
  }
  querySelector("#sample_text_id").text = buffer.toString();*/
  window.alert("Heyy!!");
}
 
