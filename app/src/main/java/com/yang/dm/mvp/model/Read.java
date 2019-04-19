package com.yang.dm.mvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Describe:
 * Created by Yang on 2019/1/29.
 */
public class Read {

    /**
     * _id : 5ba1c0809d212264e9faeb76
     * content :
     * cover : null
     * crawled : 1537326552465
     * created_at : 2018-09-19T03:20:32.342Z
     * deleted : false
     * published_at : 2018-09-19T03:09:12.465Z
     * raw : {'id': 'BhLdVdHtqe/ildR7uAUK4lQXg1H9dhptCgd1/qFE+ig=_165efcd9591:7281e1:4c71e4fe', 'originId': 'https://blog.csdn.net/lmj623565791/article/details/82766956', 'fingerprint': '8d8d3f40', 'title': '[原]我平时的一个学习方法', 'crawled': 1537326552465, 'published': 1537326552465, 'origin': {'streamId': 'feed/http://blog.csdn.net/lmj623565791/rss/list', 'title': 'Hongyang', 'htmlUrl': 'https://blog.csdn.net/lmj623565791'}, 'alternate': [{'href': 'https://blog.csdn.net/lmj623565791/article/details/82766956', 'type': 'text/html'}], 'author': 'lmj623565791', 'summary': {'content': '<h2><a></a>概述</h2>\n<p>相信大家都有通过看书、看博客学习的时候，但是很多时候，我们看完一篇又一篇博客，一本又一本书，过几天看到相同的内容时，还是觉得很陌生。</p>\n<p>所以我今天想跟大家分享下，如何更好的压缩、记忆知识。</p>\n<p>大家先看一个例子，下面有7个成语：</p>\n<ol>\n<li>张牙舞爪</li>\n<li>有的放矢</li>\n<li>鸿鹄之志</li>\n<li>帅气逼人</li>\n<li>不折不扣</li>\n<li>行为举止</li>\n<li>洋洋得意</li>\n</ol>\n<p>假设想要记住这7个成语，按照以前我上学时的方式，多读几遍，强迫自己记忆，可以在短时间内记住，但是过了一小时，两小时，一天，两天后，就忘了。</p>\n<p>有同学说，我还有个诀窍，可以把短期记忆变成长期记忆，很简单，每隔1小时回忆一下，半天回忆一下，慢慢的就记住了，按照递增的时间周期不断重复记忆即可。</p>\n<p>不过这样做太浪费时间了，下面我们看一个更有意思的记忆方式，还是刚才的词语，我们重新排列一下顺序：</p>\n<ol>\n<li>张牙舞爪</li>\n<li>鸿鹄之志</li>\n<li>洋洋得意</li>\n<li>帅气逼人</li>\n<li>有的放矢</li>\n<li>不折不扣</li>\n<li>行为举止</li>\n</ol>\n<p>有没有发现什么？</p>\n<p>没有…</p>\n<p>如果我把每个词语抽取一个字出来：</p>\n<p><strong>张鸿洋帅的不行</strong></p>\n<p>记住这句话，我估计你短期内都忘不了这句话了，根据这句话推到那7个成语，也是件非常轻松的事情。</p>\n<p>其实这也是我们说的，不要死记硬背，要用推导的方式去记忆。</p>\n<p>当然了，你可能要说，你这都是准备好的词语，实际生活中哪有这么巧的事情，说的没错，实际生活中很难有这么完美的压缩知识的例子。所以接下来我准备用一个实际的案例来分享下经历。</p>\n<h2><a></a>案例</h2>\n<p>上周我在地铁上看一篇朱赟老师的文章，讲 A/B 测试的，我以前不怎么关注这些，不过来到了头条之后，发现 A/B 测试用的非常好，所以对这个文章饱含兴趣，也想学习下，对于 A/B 测试需要关注的点，可以拿来衡量工作上的实际场景。</p>\n<p>文章中对于 A/B 测试提到了10个点：</p>\n<ol>\n<li>永远不要过分相信你的直觉；</li>\n<li>实验样本的数量和分配很重要；</li>\n<li>分析的维度尽可能全面；</li>\n<li>其它组的改动对 A/B 测试产生的影响；</li>\n<li>比较值的趋势必须是收敛的，而不是发散的；</li>\n<li>数据埋点，数据的埋点和采集是 A/B 测试成功的关键；</li>\n<li>形成一个流程，或者设计一个工具；</li>\n<li>试图给每个结果一个合理的解释；</li>\n<li>必要的时候重新设计实验；</li>\n<li>不同客户端分开进行实验；</li>\n</ol>\n<p>我的第一想法是记住这10个点，便于以后和产品做 A/B 的时候吹一吹。</p>\n<p>我心里默读了好几遍，但是10条实在是太多了，关掉手机就忘了。</p>\n<p>于是，我打开便签，把上面的内容作了重新调整：</p>\n<pre><code>不要相信自己的直觉\n\n样本数量分配\n防止其他实验影响\n必要时重新实验\n\n考虑不同维度\n结果应该收敛\n不同平台单独分析\n给每个结构一个合理的解释\n\n埋点\n形成流程\n</code></pre>\n<p>是不是觉得没什么变化，其实变化有两点：</p>\n<ul>\n<li>文字少了；</li>\n<li>分类了；</li>\n</ul>\n<p>可以看到我分成了四类，哪四类呢？</p>\n<pre><code>要做 AB 测试 -> 如何做 AB 测试 -> 如何分析 AB 测试结果 -> 对整个过程总结。\n</code></pre>\n<p>这四类因为是递进的关系，非常容易记忆。</p>\n<p>于是现在就变成:</p>\n<pre><code>要做 AB 测试;\n\t不要相信自己的直觉\n\n如何做 AB 测试;\n\t样本数量分配\n\t防止其他实验影响\n\t必要时重新实验\n\n如何分析 AB 测试结果;\n\t考虑不同维度\n\t结果应该收敛\n\t不同平台单独分析\n\t给每个结构一个合理的解释\n\t\n对整个过程总结\n\t埋点\n\t形成流程\n</code></pre>\n<p>是不是好记了很多，有分类在，10条知识也清晰了很多。</p>\n<p>当然还没有结束。</p>\n<p>接下来我又做了一件事情，因为第二、第三类，条数还是太多：</p>\n<pre><code>2. 如何做 AB 测试;\n    - 样本数量分配\n    - 防止其他实验影响\n    - 必要时重新实验\n</code></pre>\n<p>提取关键词，变成：</p>\n<p>样本、独立、重复 -> <strong>独立重复样本</strong></p>\n<p>我们也给第三类提取关键词：</p>\n<pre><code>3. 如何分析 AB 测试结果;\n    - 考虑不同维度\n    - 结果应该收敛\n    - 不同平台单独分析\n    - 给每个结构一个合理的解释\n</code></pre>\n<p>提取关键词：</p>\n<p><strong>多维度</strong>的对<strong>不同平台</strong>做单独分析，对<strong>收敛</strong>的结果给出<strong>解释</strong></p>\n<p>ok，现在10条变成了：</p>\n<pre><code>1. 要做 AB 测试;\n\t- 不要相信自己的直觉\n\n2. 如何做 AB 测试;\n\t- 独立重复样本\n\n3. 如何分析 AB 测试结果;\n\t- 多维度的给平台单独分析，对收敛的结果给出解释\n\n4. 对整个过程总结\n\t- 埋点、形成流程\n</code></pre>\n<p>如果你是在纸质笔记上完成了这项工作，你还可以：</p>\n<img alt="屏幕快照 2018-09-18 下午7.24.10.png" src="http://wanandroid.com/blogimgs/46c128f6-c55b-4d56-90e1-4b508e76ecf0.png">\n<p>给你压缩后的词句或者句子，把核心词语标注出来，提醒自己标注需要扩展。</p>\n<p>好了，以上这个过程就是我记住 AB 测试这篇文章的流程，任何时间我只要想要回忆都能回忆起来。要是以前的方式来记忆，我估计10分钟后，我就只能记住两条了。</p>\n<h2><a></a>总结</h2>\n<p>今天我通过了一篇实际的例子给大家分享下我在平时是如何记忆这些琐碎的知识的，可以看到今天这个文章还是比较贴近实际的。</p>\n<p>最后注意：</p>\n<p><strong>压缩出来的句子或者词语不一定需要具有实际的意义</strong></p>\n<p>就像文章开始你通过张鸿洋帅的不行来尝试记忆7个成语，实际上张鸿洋并不帅气，但这个句子仍然帮助你完成了记忆。</p>\n<p>还记得高中时候要记忆9种人体必需的氨基酸，老师也是使用类似的方式帮助我们去记忆，话说你们高中时候9种氨基酸是硬背还是有什么技巧，欢迎分享。</p>\n<p>最后这篇文章上述的行为，都是在地铁上完成的，mark 一个图作为纪念。</p>\n<img width="320px" alt="屏幕快照 2018-09-18 下午7.24.42.png" src="http://wanandroid.com/blogimgs/daf5ae2b-4edd-43d9-bce6-45d4202ea759.png">\n<p>以上就是我想分享给大家的，但是不一定适合大家，请抱着质疑的态度去尝试，如果对你有用，倍感荣幸。</p>\n<div>\n作者：lmj623565791 发表于 2018/09/19 08:59:59 <a href="https://blog.csdn.net/lmj623565791/article/details/82766956">原文链接</a> https://blog.csdn.net/lmj623565791/article/details/82766956 </div>\n<div>\n阅读：39 </div>', 'direction': 'ltr'}, 'unread': True}
     * site : {"cat_cn":"Android","cat_en":"android","desc":"生命不息，奋斗不止，万事起于忽微，量变引起质变","feed_id":"feed/http://blog.csdn.net/lmj623565791/rss/list","icon":"http://ww2.sinaimg.cn/large/0066P23Wjw1f9u33570ncj300g00g0mc.jpg","id":"hongyang","name":"Hongyang","subscribers":301,"type":"rss","url":"http://blog.csdn.net/lmj623565791"}
     * title : [原]我平时的一个学习方法
     * uid : BhLdVdHtqe/ildR7uAUK4lQXg1H9dhptCgd1/qFE+ig=_165efcd9591:7281e1:4c71e4fe
     * url : https://blog.csdn.net/lmj623565791/article/details/82766956
     */

    @SerializedName("_id")
    private String id;
    private String content;
    private Object cover;
    private long crawled;
    private String created_at;
    private boolean deleted;
    private String published_at;
    private String raw;
    private SiteBean site;
    private String title;
    private String uid;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getCover() {
        return cover;
    }

    public void setCover(Object cover) {
        this.cover = cover;
    }

    public long getCrawled() {
        return crawled;
    }

    public void setCrawled(long crawled) {
        this.crawled = crawled;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public SiteBean getSite() {
        return site;
    }

    public void setSite(SiteBean site) {
        this.site = site;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class SiteBean {
        /**
         * cat_cn : Android
         * cat_en : android
         * desc : 生命不息，奋斗不止，万事起于忽微，量变引起质变
         * feed_id : feed/http://blog.csdn.net/lmj623565791/rss/list
         * icon : http://ww2.sinaimg.cn/large/0066P23Wjw1f9u33570ncj300g00g0mc.jpg
         * id : hongyang
         * name : Hongyang
         * subscribers : 301
         * type : rss
         * url : http://blog.csdn.net/lmj623565791
         */

        private String cat_cn;
        private String cat_en;
        private String desc;
        private String feed_id;
        private String icon;
        private String id;
        private String name;
        private int subscribers;
        private String type;
        private String url;

        public String getCat_cn() {
            return cat_cn;
        }

        public void setCat_cn(String cat_cn) {
            this.cat_cn = cat_cn;
        }

        public String getCat_en() {
            return cat_en;
        }

        public void setCat_en(String cat_en) {
            this.cat_en = cat_en;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getFeed_id() {
            return feed_id;
        }

        public void setFeed_id(String feed_id) {
            this.feed_id = feed_id;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSubscribers() {
            return subscribers;
        }

        public void setSubscribers(int subscribers) {
            this.subscribers = subscribers;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
