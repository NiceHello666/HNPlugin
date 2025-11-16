# HNPlugin

## 目前实现功能：

### - 修改上线下线消息

### - 发送公告

### - 自定义死亡消息



# 自v1.3版本开始，需要重置lang.yml和modules.yml文件，注意哦！



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

# 自杀功能

Suicide:

 Enable: true

```



## lang.yml

```

# 插件前缀

Prefix: "&b&lHNPlugin &f&7>&f"

# 没有权限时提示消息 [%prefix% - 插件前缀]

NoPermission: "%prefix% &c你没有权限!"

# 不能在控制台执行的指令提示

CantUseInConsole: "%prefix% &c你不能在控制台上执行这个指令!"

# 重载提示

ReloadMessage: "%prefix% &a插件已重载!"

# 加入提示 [可用变量符: %player% - 玩家名,%prefix% - 插件前缀]

JoinMessage: "&8[&a+&8] &7%player%"

# 退出提示 [可用变量符: %player% - 玩家名,%prefix% - 插件前缀]

QuitMessage: "&8[&c-&8] &7%player%"

# 公告的前缀 [可用变量符: %prefix% - 插件前缀]

BCPrefix: "%prefix% &b[公告] &f"

# 死亡消息(广播, 向全体玩家发送) [可用变量符: %player% - 玩家名,%prefix% - 插件前缀,%death_x% - 死亡的x轴,%death_y% - 死亡的y轴,%death_z% - 死亡的z轴,%death_world% - 死亡的世界]

DeathBroadcast: "%prefix% &e%player% 消逝了"

# 死亡消息(私信, 向死亡玩家发送) [可用变量符: %player% - 玩家名,%prefix% - 插件前缀,%death_x% - 死亡的x轴,%death_y% - 死亡的y轴,%death_z% - 死亡的z轴,%death_world% - 死亡的世界]

DeathMsg: "%prefix% &e你消逝了,死亡点在%death_world%, %death_x%, %death_y%, %death_z%"

# 自杀广播 [可用变量符: %player% - 玩家名,%prefix% - 插件前缀]

SuicideBroadcast:  "%prefix% &c%player% 自杀了..."

```



## 插件权限

 ### hnplugin.command.main - /hnp指令及其子命令

 ### hnplugin.command.suicide - /suicide自杀指令
