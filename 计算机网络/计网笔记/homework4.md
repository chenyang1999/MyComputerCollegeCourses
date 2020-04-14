# homework4

陈扬

17150011001

4,5,6,7,9,10,13,17,19,20,21,22. 24,26,28,29,30,41,42,54

---

##### 4-04 试简单说明下列协议的作用

IP, ARP, RARP 和 ICMP

> IP:The **Internet Protocol** (**IP**) is the principal [communications protocol](https://en.wikipedia.org/wiki/Communications_protocol) in the [Internet protocol suite](https://en.wikipedia.org/wiki/Internet_protocol_suite) for relaying [datagrams](https://en.wikipedia.org/wiki/Datagram) across network boundaries. Its [routing](https://en.wikipedia.org/wiki/Routing) function enables [internetworking](https://en.wikipedia.org/wiki/Internetworking), and essentially establishes the [Internet](https://en.wikipedia.org/wiki/Internet).
>
> ARP:The **Address Resolution Protocol** (**ARP**) is a [communication protocol](https://en.wikipedia.org/wiki/Communication_protocol) used for discovering the [link layer](https://en.wikipedia.org/wiki/Link_layer) address, such as a [MAC address](https://en.wikipedia.org/wiki/MAC_address), associated with a given [internet layer](https://en.wikipedia.org/wiki/Internet_layer) address, typically an [IPv4 address](https://en.wikipedia.org/wiki/IPv4_address). This mapping is a critical function in the [Internet protocol suite](https://en.wikipedia.org/wiki/Internet_protocol_suite). 
>
> RARP:The **Reverse Address Resolution Protocol** (**RARP**) is an obsolete computer networking protocol used by a client computer to request its Internet Protocol ([IPv4](https://en.wikipedia.org/wiki/IPv4)) address from a computer network, when all it has available is its [link layer](https://en.wikipedia.org/wiki/Link_layer) or hardware address, such as a [MAC address](https://en.wikipedia.org/wiki/MAC_address). The client broadcasts the request and does not need prior knowledge of the network topology or the identities of servers capable of fulfilling its request.
>
> ICMP:The **Internet Control Message Protocol** (**ICMP**) is a supporting [protocol](https://en.wikipedia.org/wiki/Communications_protocol) in the [Internet protocol suite](https://en.wikipedia.org/wiki/Internet_protocol_suite). It is used by [network devices](https://en.wikipedia.org/wiki/Network_device), including [routers](https://en.wikipedia.org/wiki/Router_(computing)), to send error messages and operational information indicating success or failure when communicating with another IP address, for example, an error is indicated when a requested service is not available or that a [host](https://en.wikipedia.org/wiki/Host_(network)) or router could not be reached.[[1\]](https://en.wikipedia.org/wiki/Internet_Control_Message_Protocol#cite_note-Forouzan-1) 

----

4-05 IP 地址分为几类？各如何表示？IP 地址的主要特点是什么

> In the original design of IPv4, an IP address was divided into two parts: the network identifier was the most significant octet of the address, and the host identifier was the rest of the address. The latter was also called the *rest field*. This structure permitted a maximum of 256 network identifiers, which was quickly found to be inadequate.
>
> To overcome this limit, the most-significant address octet was redefined in 1981 to create *network classes*, in a system which later became known as [classful networking](https://en.wikipedia.org/wiki/Classful_network). The revised system defined five classes. Classes A, B, and C had different bit lengths for network identification. The rest of the address was used as previously to identify a host within a network. Because of the different sizes of fields in different classes, each network class had a different capacity for addressing hosts. In addition to the three classes for addressing hosts, Class D was defined for [multicast](https://en.wikipedia.org/wiki/Multicast) addressing and Class E was reserved for future applications.

---

4-06 试根据 IP 地址的规定，计算出表 4-2 中的各项数据。

![image-20191127160422554](https://cy-1256894686.cos.ap-beijing.myqcloud.com/2019-11-27-080422.png)



|        A类地址:         |                     |         B类地址:         |                  |         C类地址:         |                          |
| :---------------------: | ------------------- | :----------------------: | ---------------- | :----------------------: | :----------------------: |
|    最大可指派网络数:    | $2^{7}-2=126$       |    最大可指派网络数:     | $2^{14}-2=16383$ |    最大可指派网络数:     |    $2^{14}-2=2097151$    |
|  第一个可指派的网络号:  | 00000001            |  第一个可指派的网络号:   | 1000000000000001 |  第一个可指派的网络号:   | 110000000000000000000001 |
| 最后一个可指派的网络号: | 01111110            | 最后一个可指派的网络号:  | 1011111111111111 |  最后一个可指派的网络号  | 110111111111111111111111 |
| 每个网络中的最大主机数: | $2^{24}-2=16777214$ | 每个网络中的最大主机数:: | $2^{16}-2=16383$ | 每个网络中的最大主机数:: |      $2^{8}-2=254$       |



---

4-07 试说明 IP 地址与硬件地址的区别。为什么要使用这两种不同的地址？

IP地址是全世界唯一的,硬件地址是有硬件产商生产的时候预设的,实际用户使用的时候可以更改MAC地址.

IP地址是网络层及以上使用的地址,而MAC地址是链路层和物理层使用的地址.

在传输的过程中通过ARP和RARP实现IP地址和MAC地址的映射



---

4-09

(1) 子网掩码为 255.255,255.0 代表什么意思:

是C类地址的子网掩码,意味着网段内只有8bit(254)台IP地址可配置.

(2) 一个网络的现在掩码为 255.255.255.248, 问该网络能够连接多少台主机？

$(248)_{10}=(11111000)_2$

256-248-2=6台

(3) 一个 A 类网络和一个 B 类网络的子网号 subnet-id 分别为 16 个 1 和 8 个 1, 问这两个网络的子网掩码有何不同

A:8+16=24个1

B:16+8=24个1

子网掩码一样,子网数目不一样

(4) 一个 B 类地址的子网掩码是 255,250.240.0。试问在其中每一个子网上的主机数最多是多少？

$(240)_{10}=(11110000)_2$

16+4=20个1

$2^{32-20}-2=4094$台主机

(5) 一个 A 类网络的子网掩码为 255.255.0,255, 它是否为有效的子网掩码？ 

不是,子网掩码必须是由一串连续的1和一串的0组成

(6) 某个 P 地址的十六进制表示是 C2.2E.14.81, 试将其转换为点分十进制的形式。这个地址是哪  一类 IP 地址

$(C2)_{16}=(194)_{10}$,是一个C类地址

(7) C 类网络使用子网掩码有无实际意义？为什么？

有意义,就算是一个C类网络,仍然可以划分成多个子网



---

4-10 试辨认以下 IP 地址的网络类别：

 (1)128.36.199.3—B

 (2)21.12.240.17— A

 (3)183.194.76.253—B

 (4)192.12.69.248—C

 (5)89.3.0.1—A

 (6)200.3.6.2—C



---

4-13 设 IP 数据报使用固定首部，其各字段的具体数值如图 4-65 所示（除 P 地址外，均为十进制表示）。试用二进制运算方法计算应当写入到首部检验和字段中的数值（用二进制表示）。

![image-20191127110220126](https://cy-1256894686.cos.ap-beijing.myqcloud.com/2019-11-27-030220.png)

















































---

4-17 一个 3200 位长的 TCP 报文传到 IP 层，加上 160 位的首部后成为数据报。下面的互联网由两个局域网通过路由器连接起来，但第二个局域网所能传送的最长数据帧中的数据部分只有 1200 位，因此数据报在路由器必须进行分片。试问第二个局域网向其上层要传送多少比特的数据（这里的“数据”当然指的是局域网看见的数据）?

需要多额外3个片段:3200=1040+1040+1040+80

传输数据量为3200+160*4=3840bit



---

4-19 主机 A 发送 P 数据报给主机 B，途中经过了 5 个路由器。试问在 P 数据报的发送过程中总共使用了几次 ARP?

5个路由器,说明有6条链路,使用了6次ARP协议.



---

4-20 设某路由器建立了如下路由表：

![image-20191127164939318](https://cy-1256894686.cos.ap-beijing.myqcloud.com/2019-11-27-084940.png)

现共收到 5 个分组，其目的地址分别为

(1)128.96.39.10  

(2)128.96.40.12  

(3)128.96.40.151  

(4)192.4.153.17  

(5)192.4.153.90 

试分别计算其下一跳。

1. m0
2. R2
3. R4
4. R3
5. R4

---

4-21 某单位分配到一个 B 类 IP 地址，其 net-id 为 129.250.0.0。该单位有 4000 台机器，平均分布在 16 个不同的地点。如选用子网掩码为 255.255,255.0, 试给每一个地点分配个子网号码，并算出每个地点主机号码的最小值和最大值。

题目已经给定子网掩码,因此,我们可以选用00000001到00001000这16个子网号

400/16=250

因此最小:129.250.0.1

最大:129.259.16.250

---

4-22 个数据报长度为 4000 字节（固定首部长度）。现在经过一个网络传送，但此网络能够传送的最大数据长度为 1500 字节。试问应当划分为几个短些的数据报片？各数据报片的数据字段长度、片偏移字段和 MF 标志应为何数值？

4000/1500>2

需要3个数据报片

分别是1480+1480+1020=4000-20

片偏移字段:0,1480/8,2*1480/8

MF:1,1,0



---

4-24 试找出可产生以下数目的 A 类子网的子网掩码（采用连续掩码）

 (1)2; (2)6; (3)30; (4)62; (S)122; (6)250。

1. 11111111.11000000.00000000.00000000
2. 11111111.11100000.00000000.00000000

3. 11111111.11111000.00000000.00000000

4. 11111111.11111100.00000000.00000000

5. 11111111.11111110.00000000.00000000

6. 11111111.11111111.00000000.00000000

   

---

4-26有如下的4个/24地址块,试进行最大可能的聚合。

212.56.132.0/24

212.56.133.0/24

212.56.134.0/24 

212.56.135.0/24

> 列出第三个字节:
>
> 100100
>
> 100101
>
> 100110
>
> 100111

> 212.56.132.0/22

---

4-28 已知路由器 R1 的路由表如表 4-13 所示。

![image-20191127110703546](https://cy-1256894686.cos.ap-beijing.myqcloud.com/2019-11-27-030704.png)

试画出各网络和必要的路由器的连接拓扑，标注出必要的 IP 地址和接口。对不能确定的情况应当指明。





















---

4-29 一个自治系统有 5 个局域网，其连接图如图 4-66 所示。$LAN_2$ 至 $LAN_5$ 上的主机数分别为:91,150,3和15。该自治系统分配到的P地址块为30.138.118/23。试给出每一个局域网的地址块（包括前缀）。

![image-20191127110756781](https://cy-1256894686.cos.ap-beijing.myqcloud.com/2019-11-27-030757.png)



| Routing  |      Answer       |
| :------: | :---------------: |
| $LAN_1$  | 30.138.118.192/27 |
| $LAN_2$  |  30.138.118.0/25  |
| $LAN_3$  |  30.138.119.0/24  |
| $LAN_4$  | 30.138.118.224/27 |
| $LAN_5 $ | 30.138.118.128/27 |



---

4-30 一个大公司有一个总部和三个下属部门。公司分配到的网络前缀是 192.773324。公司的网络布局如图 4-67 所示。总部共有 5 个局域网，其中的 LAN1~LAN4 都连接到路由器 R1 上，R1 再通过 LANs 与路由器 R2 相连。R2 和远地的三个部门的局域网 LAN6~LANs 通过广域网相连。每一个局域网旁边标明的数字是局域网上的主机数。试给每一个局域网分配一个合适的网络前缀.

![image-20191127110833724](https://cy-1256894686.cos.ap-beijing.myqcloud.com/2019-11-27-030834.png)

| Routing  |      Answer       |
| :------: | :---------------: |
| $LAN_1$  |  192.77.33.0/24   |
| $ LAN_2$ | 192.77.33..96/28  |
| $LAN_3$  | 192.77.33..128/28 |
| $LAN_4$  | 192.77.33..64/27  |
| $ LAN_5$ | 192.77.33..240/29 |
| $ LAN_6$ | 192.77.33.196/27  |
| $ LAN_7$ | 192.77.33.216/27  |
| $ LAN_8$ | 192.77.33.224/27  |
| $WAN_1$  | 192.77.33.240/30  |
| $WAN_2$  | 192.77.33.246/30  |
| $WAN_3$  | 192.77.33.252/30  |
|          |                   |
|          |                   |



---

4-41 假定网络中的路由器 B 的路由表有如下的项目（这三列分别表示“目的网络”“距离”和“下一跳路由器”）:

|  N1  |  7   |  A   |
| :--: | :--: | :--: |
|  N2  |  2   |  C   |
|  N6  |  8   |  F   |
|  N8  |  4   |  E   |
|  N9  |  4   |  F   |

现在 B 收到从 C 发来的路由信息（这两列分别表示“目的网络”和“距离”）:

|  N2  |  4   |
| :--: | :--: |
|  N3  |  8   |
|  N6  |  4   |
|  N8  |  3   |
|  N9  |  5   |

试求出路由器 B 更新后的路由表（详细说明每一个步骤）。

| dest | hops | next |  STATE  |
| :--: | :--: | :--: | :-----: |
|  N1  |  7   |  A   |   NO    |
|  N2  |  2   |  C   | UPGARDE |
|  N3  |  8   |  C   |   NEW   |
|  N6  |  8   |  C   | UPGARDE |
|  N8  |  4   |  E   |  STAY   |
|  N9  |  4   |  F   |  STAY   |





---

4-42 假定网络中的路由器 A 的路由表有如下的项目（格式同上题）:

|  N1  |  4   |  B   |
| :--: | :--: | :--: |
|  N2  |  2   |  C   |
|  N3  |  1   |  F   |
|  N4  |  5   |  G   |

现在 A 收到从 C 发来的路由信息（格式同上题）