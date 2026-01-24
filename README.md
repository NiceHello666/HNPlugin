# HNPlugin

## 目前实现功能：

### - 修改加入退出消息

### - 发送公告

### - 自定义死亡消息

### - 紫砂指令

### - 强制玩家执行指令

### - 以控制台身份执行指令

### - 死亡世界名字替换



# 如果你是旧版到v1.4的升级，你需要手动补全在lang.yml与modules.yml缺失的项，或者重置文件



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

# 自杀功能(/suicide)

Suicide:

 Enable: true

# 强制执行指令功能(/sudo)

Sudo:

 Enable: true

# 以控制台身份执行指令

Console:

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

# 在执行人需要输入玩家名却写错时出现此消息 [可用变量符: %prefix% - 插件前缀]

WrongPlayer: "%prefix% &c未找到玩家"

# 强制执行指令(/sudo)执行成功提示 [可用变量符: %sudocmd% - 执行的指令,%sudoplayer% - 被强制执行指令的玩家,%prefix% - 插件前缀]

SudoFeedBack: "%prefix% &a你已使 &e%sudoplayer% &a强制执行 &e%sudocmd%"

# 以控制台身份执行指令反馈 [可用变量符: %consolecmd% - 执行的指令,%prefix% - 插件前缀]

ConsoleCommandFeedBack: "%prefix% &a你以控制台身份执行了指令 &e%consolecmd%"

# 插件世界变量符显示文本 [你可以自己加世界，仿照这个格式即可]

WorldDisplayList:

"world": "&a主世界"

  "world_nether": "&c地狱世界"

  "world_the_end": "&6末地世界"

```



## 插件权限

 ### hnplugin.command.main - /hnp指令及其子命令

 ### hnplugin.command.suicide - /suicide自杀指令

 ### hnplugin.command.sudo - /sudo强制玩家执行指令

 ### hnplugin.command.console - /console以控制台身份执行指令
