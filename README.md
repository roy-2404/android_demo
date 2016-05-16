# android_demo

Client (Android app):

The client code is a modification of the code found in http://ijoshsmith.com/2014/01/25/video-streaming-from-an-ip-camera-to-an-android-phone/

It fixes two bugs in the above code:

1. The example code initialized the window settings after the call to super.onCreate(). It had to be moved before that call, since the onCreate method needed the information about the player window.
2. Secondly, the code from the above link suggested us to use port 554 in the RTSP_URL. After lots of trials, we determined that currently the streamed video can be only received using the 8080 port.

Server:

We used VLC Media Player as the server for streaming out the video over the WiFi using RTP over RTSP. We selected a video file and then selected the streaming option. In the settings for the streaming, we selected stream type as RTP, h264 as the video encoding at 3072 kb/s Bitrate. The SDP URL used was rtsp://[our machine ip]:8080/test.sdp