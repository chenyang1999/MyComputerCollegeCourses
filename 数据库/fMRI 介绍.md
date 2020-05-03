# fMRI 介绍

知乎:https://www.zhihu.com/question/20544091/answer/16545341

功能磁共振(functional magnetic resonance imaging, fMRI)是一种无创非放射性观察大脑活动的技术，已被广泛用于神经科学及认知科学领域的研究，其主要的原理是血氧水平依赖（blood oxygenation level dependent，BOLD）。在人脑中，氧气是通过毛细血管的血红蛋白运送给神经元细胞的。当神经活动增加时，耗氧量也会随之增加，从而引起局部区域血流量的增加。当血红蛋白结合氧分子形成氧合血红蛋白时，它是反磁性的；而当氧分子脱离后，它是顺磁性的。这种磁性差异就引起了磁共振信号的微小改变，即BOLD的基本原理。需要注意的是，随着神经元活动与血流量、氧合血红蛋白间的关系是动态改变的。在神经元活动的初期，局部区域的氧含量被消耗而下降，引发一个初始的“谷点”。而随之而来的是血流的大量增加，不仅可以满足局部的氧需求，而且大大超过这一水平，因此整体的氧合血红蛋白含量反而会表现出增加。这一峰值会持续大约6秒后回落至基线水平，同时伴随一个后续的低谷。

功能磁共振的BOLD信号基于T2* 加权像，因为磁共振所能探测到的去氧血红蛋白和氧合血红蛋白所产生的信号在这一类成像方法上强度最大。典型的MRI实验是先获取一般状态下的T2* 加权像，然后让被试者在扫描过程中执行一个安排好的任务。当任务刺激开始被呈现给被试者，相应神经元的活动导致局部微环境中相对氧合血红蛋白含量的增加，也就是BOLD信号的增加，于是获取到“功能性”的T2加权像。通过比较刺激前后不同的信号强度，可以分析得出神经元活动增加的脑区，并用不同色彩的方式标注出来。

让被试者执行任务是传统的fMRI实验方法，该实验方法获取的影像一般被称为“任务态”。通过区分实验设计中最重要的是任务的范式，也就是实验过程中呈现给研究对象的刺激策略，实验可分为两大类：区组设计（Blocked designs）和事件相关设计(Event-related designs)

任务态功能磁共振研究中发现有任务不相关脑区活动的降低，以及发现静息状态下也有在时间和空间尺度上的自发神经活动。这些发现提出了一个任务态没能解答的关于大脑神经活动的根本问题：为何一个只占体重2%的器官却能占到全身能量消耗的20%？传统的任务态fMRI之揭示了一小部分脑活动，所谓“静息态”并非是完全静息状态。早在20世纪初通过脑电图（EEG）发现神经电活动呈现出8-12Hz频率的振荡。其他哺乳动物的实验表明神经元网络电活动振荡呈现出不同谱带，频率覆盖了0.05Hz – 500Hz。在多数功能磁共振的研究中，BOLD信号的振荡被当作是噪点或是干扰。随着新技术和分析方法的进展，可以通过将非神经元真正的BOLD噪声信号排除，提取出自发波动的BOLD信号部分来分析。

对静息态fMRI中表现的特定频率范围进行分析，不同的方法可以辨认出大脑中不同的空间模式。对于整个大脑中远距离区域之间活动模式，可以分离出一些所谓的网络，比如默认网络，执行控制等等，常见的方法有感兴趣区域（Region of Interest, ROI），独立成分分析（Independent Component Analysis, ICA），分级群聚（Hierarchical Clustering, HC）。另外还有一些局部模式，包括局部一致性（Regional Homogeneity, ReHo）、低频振幅（Amplitude of Low Frequency Fluctuation, ALFF）等。



作为心理学最流行，最高端，最大气的研究方法，首先，当然必须要了解心理学！尤其是认知心理学。推荐<Cognition, Brain, and Consciousness> by Baars
然后生物学基础必不可少，神经解剖学看一遍吧。譬如奈特神经解剖学
数据分析可能是重中之重，首推 ＜Handbook of Functional MRI Data Analysis＞，目前译言网有部分中文翻译 [fMRI数据分析手册翻译](https://link.zhihu.com/?target=http%3A//group.yeeyan.org/fmri)
做不起MRI实验，手头没有数据怎么办？没关系，网上有公开的数据资源可以利用，譬如 1000 [Functional Connectomes](https://link.zhihu.com/?target=http%3A//www.nitrc.org/ir/app/template/XDATScreen_report_xnat_projectData.vm/search_element/xnat%3AprojectData/search_field/xnat%3AprojectData.ID/search_value/fcon_1000) ，提供了无穷的静息态原始数据，甚至可以挖掘这些数据发表论文。
有一台不算差的电脑，装上Matlab，安装 [SPM8 - Statistical Parametric Mapping](https://link.zhihu.com/?target=http%3A//www.fil.ion.ucl.ac.uk/spm/software/spm8/) ，还有[REST](https://link.zhihu.com/?target=http%3A//www.restfmri.net/forum/) 就可以开始入门了。
有中文社区，包括上面的[REST](https://link.zhihu.com/?target=http%3A//www.restfmri.net/forum/) 和 [心心水滴论坛](https://link.zhihu.com/?target=http%3A//52brain.com/forum.php)
心理学和医学僧们最好补习一点点线性代数知识；一定要弄明白基础统计学，推荐Coursera 课程 [Statistics One](https://link.zhihu.com/?target=https%3A//class.coursera.org/stats1-002/lecture/index)，书籍推荐《行为科学统计》，倒腾data的想法就靠他们了

---

