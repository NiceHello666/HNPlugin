# HNPlugin

## 目前实现功能：
### - 修改上线下线消息
### - 发送公告
### - 自定义死亡消息

# 自v1.2版本开始，将config.yml拆成了lang.yml和modules.yml这2个文件，注意哦！

## modules.yml
```
# 是否开启模块 [true - 开启,false - 关闭]
# -----
#加入消息
JoinMessage:
  Enable: true
# 退出消息
QuitMessage:
  Enable: true
# 死亡消息(广播, 向全体玩家发送)
DeathBroadcast:
  Enable: true
# 死亡消息(私信, 向死亡玩家发送)
DeathMsg:
  Enable: true
```

## lang.yml
```
# 插件前缀
Prefix: "&b&lHNC &f&7>&f"
# 重载提示
ReloadMessage: "%prefix% &a插件已重载!"
# 加入提示 [可用变量符: %player% - 玩家名,%prefix% - 插件前缀]
JoinMessage: "&8[&a+&8] &7%player%"
# 退出提示 [可用变量符: %player% - 玩家名,%prefix% - 插件前缀]
QuitMessage: "&8[&c-&8] &7%player%"
# 公告的前缀 [可用变量符: %prefix% - 插件前缀]
BCPrefix: "%prefix% &b[公告] %player% > &f"
# 死亡消息(广播, 向全体玩家发送) [可用变量符: %player% - 玩家名,%prefix% - 插件前缀,%death_x% - 死亡的x轴,%death_y% - 死亡的y轴,%death_z% - 死亡的z轴]
DeathBroadcast: "%prefix% &e%player% 消逝了"
# 死亡消息(私信, 向死亡玩家发送) [可用变量符: %player% - 玩家名,%prefix% - 插件前缀,%death_x% - 死亡的x轴,%death_y% - 死亡的y轴,%death_z% - 死亡的z轴]
DeathMsg: "%prefix% &e你消逝了,死亡点在%death_x%, %death_y%, %death_z%"
```
