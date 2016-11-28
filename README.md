# RGitHub

RGitHub. A Quick GitHub Client.

一个 GitHub 客户端，加载内容会缓存，应用未关闭情况下，可能获取到的更新不够及时，但考虑到 GitHub 的特点，这样做是可以接受的。本项目开发动机为现有 GitHub 客户端都不能满足我的日常需求，查看消息会重复加载，缓慢，并且有太多用不到的功能，我的需求是可以经常查看 GitHub 上一些好的项目和关注的人的动态，并可以查找项目，对项目进行简单了解。具体操作不多，看代码不多，速度要快。感谢 Coding.net，要是 GitHub 有像 Coding 一样好用的（或者说适合我的）客户端，那也就没有此项目了。基于这样的原因，开发了本项目。项目 [代码](https://github.com/RWebRTC/RGitHub) 。

# 简介

1. 采用内存缓存，在应用打开一次过程中，内存缓存下载过的内容，重复页面不重复加载，并且没有强制刷新方法，只能退出应用重新打开。这样做的原因是 GitHub 本身动态不多，更新不频繁。这样做节省流量，速度快。

2. 不重复造轮子，一些查看操作，用手机浏览器可以很好满足需求，因此，一些操作（自动更新下载，代码查看等）直接跳转浏览器对应界面，方便查看，并可进行交互操作。

3. 由于 GitHub Treading（热门项目）API 没有开放，大多客户端不好查看最新最热项目，并且现有GitHub Android API 过于复杂，导致应用过大，因此在开发本项目同时，开发子项目（RGitHubAndroidSDK：GitHub 访问库，AutoUpdate：GitHub Release 自动更新库），可重用，并能满足其他应用没有满足需求。其中在开发过程中参考大量其他开源项目，感谢他们。

# Download

<a href="https://play.google.com/store/apps/details?id=cn.renyuzhuo.rgithub"><img alt="Get it on Google Play" width="160" src="https://cloud.githubusercontent.com/assets/21374839/20084339/9613a18a-a59c-11e6-8db6-86d0ae0b84f8.png"/></a>

From [GitHub](https://github.com/RWebRTC/RGitHub/raw/develop/app-release.apk)

From [Coding](https://coding.net/u/rwebrtc/p/RGitHub/git/raw/master/app-release.apk) ( The Fastest From China )

# Screenshot

<img src="https://cloud.githubusercontent.com/assets/21374839/20625591/3e7ebb38-b34f-11e6-8a81-f62edf8bc474.png" width="250"/> 
<img src="https://cloud.githubusercontent.com/assets/21374839/20625592/3e82a95a-b34f-11e6-8577-415cbf5878ba.png" width="250"/> 
<img src="https://cloud.githubusercontent.com/assets/21374839/20625587/3e1ab0a2-b34f-11e6-868e-5a0418c740fd.png" width="250"/> 
<img src="https://cloud.githubusercontent.com/assets/21374839/20625594/3eb4c99e-b34f-11e6-8a5d-850b97493150.png" width="250"/> 
<img src="https://cloud.githubusercontent.com/assets/21374839/20625590/3e513b18-b34f-11e6-9eec-5816cef60de3.png" width="250"/> 
<img src="https://cloud.githubusercontent.com/assets/21374839/20625589/3e4d45da-b34f-11e6-8fba-23aaf9fb420f.png" width="250"/> 
<img src="https://cloud.githubusercontent.com/assets/21374839/20625588/3e1f2254-b34f-11e6-87cd-ec3b3c14c29b.png" width="250"/> 
<img src="https://cloud.githubusercontent.com/assets/21374839/20625593/3eb03302-b34f-11e6-9185-d41d2c19e7da.png" width="250"/> 

# Thanks

[Json工具](http://www.sojson.com/json2entity.html)

[GitClub](https://github.com/TellH/GitClub)

[CodeHub](http://codehub-app.com/)

[Coding.net](https://coding.net)

……

# GitHub API

TODO 说明

# TODO

- MarkDown README
- 横屏显示图标（需要有审美细胞的同志帮助）

# LICENSE

[MIT](http://renyuzhuo.cn/MIT)

