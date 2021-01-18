package com.jmu.onlinecourse.utils;

import com.jmu.onlinecourse.entity.Courseware;
import com.jmu.onlinecourse.entity.VideoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zjw
 * @date 2021/1/12 17:47
 * @ClassName DataProviderUtils
 */
public class DataProviderUtils {
    private final static int FIVE = 20;

    private static List<VideoInfo> list = new ArrayList<>();
    private static List<Courseware> coursewares = new ArrayList<>();
    static {
        VideoInfo videoInfo1 = new VideoInfo();
        videoInfo1.setVideoName("秋收起义");
        videoInfo1.setSummary("1927年，蒋介石背叛革命，一片白色恐怖弥漫在华夏大地上空。中国共产党于8月7日召开了紧急会议，委派毛泽东前往湖南组织秋收武装暴动。毛泽东决定成立工农革命军第一军第一师，并将暴动时间定为1927年9月9日。毛泽东和潘心源、陈志安走到张家坊，却被阎仲甫所设的哨卡拦住。几个团丁押着三人走在山路上，毛泽东巧施妙计得以脱身，三人来到铜鼓镇工农革命军第三团集结地。秋收起义终于打响了。工农革命军一路攻下多处要害后遭到敌人包围，因敌强我弱，部队损失惨重。毛泽东仔细思考局势，在会上指出敌我双方实力悬殊，我方应先站稳脚跟，保存实力。随后，毛泽东带领部队前往敌人统治力量最为薄弱的罗霄山脉，建立苏维埃政权。部队终于冲破敌人的围剿，毛泽东在几百名战士的簇拥下明确了拉着队伍上井冈的部署，坚定地表示必将打倒蒋介石。");
        videoInfo1.setImageUrl("https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2371889280.webp");
        videoInfo1.setDetailUrl("https://vkceyugu.cdn.bspapp.com/VKCEYUGU-imgbed/aedd12c4-4b16-4e8c-b88e-7944d32e1516.mp4");
        videoInfo1.setLikes(100);

        VideoInfo videoInfo2 = new VideoInfo();
        videoInfo2.setVideoName("小平您好");
        videoInfo2.setSummary("为了向邓小平同志诞辰100周年献礼，由中央文献研究室、中央电视台、中央新闻纪录电影制片厂、江苏省广播电视总台联合摄制的文献纪录片《小平您好》以邓小平的经历和历史风云为背景，通过细节来叙述他的个性风采和情感世界。编创人员从半个多世纪积累的数万尺胶片中，披沙拣金，撷取大量珍贵的镜头，让观众体会口传故事中无法体会的历史真切，感受一代伟人的智慧胆识，聆听老人发自肺腑的心声，品味领袖生活的朴实无华。");
        videoInfo2.setImageUrl("https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2507753120.webp");
        videoInfo2.setDetailUrl("https://vkceyugu.cdn.bspapp.com/VKCEYUGU-imgbed/2bf2960d-5258-48ba-ac3f-c7b95006da5b.mp4");

        VideoInfo videoInfo3 = new VideoInfo();
        videoInfo3.setVideoName("东方红");
        videoInfo3.setSummary("  电影《东方红》的拍摄，对1964年的舞台演出版本进行了必要的删减。把舞台剧原有的8场，只选取了序幕“东方红”和前6场的“东方的曙光”、“星火燎原”、“万水千山”、“抗日的烽火”、“埋葬蒋家王朝”、“中国人民站起来”，而后两场的“祖国在前进”和“世界在前进”，则遵照毛泽东的意见，没有收入到电影画面中去。\n" +
                "  在拍摄演出过程时，大部分画面采用多机位拍摄，而舞台上的许多集体亮相和群众演员的造型，则采用舞台全景和台下观众相互融合在一个画面的广角镜头，以显示出舞台演出的盛大规模，同时，也是把周恩来所反复强调的“不要离开舞台”的指示，很好地进行了艺术化的处理，从而使整部电影画面流动，情景交融，而不是机械式地一个机位固定拍摄。所以，拍摄完成的舞台艺术纪录片，既丝毫没有脱离舞台，又大胆创新，把舞台演出和艺术纪录完美地融合在一起，为今后同类样式的电影拍摄，开辟了一条崭新的道路。");
        videoInfo3.setImageUrl("https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2329410348.webp");
        videoInfo3.setDetailUrl("https://vkceyugu.cdn.bspapp.com/VKCEYUGU-imgbed/d472a1a6-7faa-46fc-8819-6ddd147e660a.mp4");

        VideoInfo videoInfo4 = new VideoInfo();
        videoInfo4.setVideoName("东方红——音乐舞蹈史诗");
        videoInfo4.setSummary("《东方红 音乐舞蹈史诗》音乐舞蹈史诗表现的是中国人民的历史，它是建国以来文艺演出界的一大盛事，堪称为“20世纪经典”。本碟为1965年强大演出阵容完整版。\n" +
                "　　1964年，音乐舞蹈史诗《东方红》轰动了全国，其规模之宏大、水平之高、影响之广泛，是史无前例的。\n" +
                "　　这部鸿篇巨制是在周恩来总理的倡导下进行的，以歌、舞、诗三位一体的形式，描写了中国人民从苦难走向胜利的艰苦历程。这部作品以严谨的结构、宏伟的气势、精巧的设计和高超的技艺来展现主题，根据主题发展的需要，穿插了大量的新编作品、各个历史时期在群众中广为流传的优秀歌曲、建国以来的优秀舞蹈曲目等等。");
        videoInfo4.setImageUrl("https://img9.doubanio.com/view/subject/m/public/s2766474.jpg");
        videoInfo4.setDetailUrl("https://vkceyugu.cdn.bspapp.com/VKCEYUGU-imgbed/4880a114-b71c-4ebd-ac58-2e3be423be1e.mp4");

        VideoInfo videoInfo5 = new VideoInfo();
        videoInfo5.setVideoName("永远的小平");
        videoInfo5.setSummary("电视专题片《永远的小平》由凤凰卫视摄制，以邓小平的家人、身边工作人员作为主要的采访对象，其夫人、儿女、妹妹等面对镜头，讲述了他们心目中的邓小平。其中，邓小平与夫人卓琳心心相映、患难与共、相伴走过58个风云多变的春夏秋冬的故事尤为感人。");
        videoInfo5.setImageUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3347870091,3555500830&fm=26&gp=0.jpg");
        videoInfo4.setDetailUrl("https://vkceyugu.cdn.bspapp.com/VKCEYUGU-imgbed/4880a114-b71c-4ebd-ac58-2e3be423be1e.mp4");

        VideoInfo videoInfo6 = new VideoInfo();
        videoInfo6.setVideoName("永远的小平1");
        videoInfo6.setSummary("电视专题片《永远的小平》由凤凰卫视摄制，以邓小平的家人、身边工作人员作为主要的采访对象，其夫人、儿女、妹妹等面对镜头，讲述了他们心目中的邓小平。其中，邓小平与夫人卓琳心心相映、患难与共、相伴走过58个风云多变的春夏秋冬的故事尤为感人。");
        videoInfo6.setImageUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3347870091,3555500830&fm=26&gp=0.jpg");
        videoInfo4.setDetailUrl("https://vkceyugu.cdn.bspapp.com/VKCEYUGU-imgbed/4880a114-b71c-4ebd-ac58-2e3be423be1e.mp4");


        VideoInfo videoInfo7 = new VideoInfo();
        videoInfo7.setVideoName("永远的小平2");
        videoInfo7.setSummary("电视专题片《永远的小平》由凤凰卫视摄制，以邓小平的家人、身边工作人员作为主要的采访对象，其夫人、儿女、妹妹等面对镜头，讲述了他们心目中的邓小平。其中，邓小平与夫人卓琳心心相映、患难与共、相伴走过58个风云多变的春夏秋冬的故事尤为感人。");
        videoInfo7.setImageUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3347870091,3555500830&fm=26&gp=0.jpg");
        videoInfo4.setDetailUrl("https://vkceyugu.cdn.bspapp.com/VKCEYUGU-imgbed/4880a114-b71c-4ebd-ac58-2e3be423be1e.mp4");

        VideoInfo videoInfo8 = new VideoInfo();
        videoInfo8.setVideoName("永远的小平3");
        videoInfo8.setSummary("电视专题片《永远的小平》由凤凰卫视摄制，以邓小平的家人、身边工作人员作为主要的采访对象，其夫人、儿女、妹妹等面对镜头，讲述了他们心目中的邓小平。其中，邓小平与夫人卓琳心心相映、患难与共、相伴走过58个风云多变的春夏秋冬的故事尤为感人。");
        videoInfo8.setImageUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3347870091,3555500830&fm=26&gp=0.jpg");
        videoInfo4.setDetailUrl("https://vkceyugu.cdn.bspapp.com/VKCEYUGU-imgbed/4880a114-b71c-4ebd-ac58-2e3be423be1e.mp4");

        VideoInfo videoInfo9 = new VideoInfo();
        videoInfo9.setVideoName("永远的小平4");
        videoInfo9.setSummary("电视专题片《永远的小平》由凤凰卫视摄制，以邓小平的家人、身边工作人员作为主要的采访对象，其夫人、儿女、妹妹等面对镜头，讲述了他们心目中的邓小平。其中，邓小平与夫人卓琳心心相映、患难与共、相伴走过58个风云多变的春夏秋冬的故事尤为感人。");
        videoInfo9.setImageUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3347870091,3555500830&fm=26&gp=0.jpg");
        videoInfo4.setDetailUrl("https://vkceyugu.cdn.bspapp.com/VKCEYUGU-imgbed/4880a114-b71c-4ebd-ac58-2e3be423be1e.mp4");

        VideoInfo videoInfo10 = new VideoInfo();
        videoInfo10.setVideoName("永远的小平5");
        videoInfo10.setSummary("电视专题片《永远的小平》由凤凰卫视摄制，以邓小平的家人、身边工作人员作为主要的采访对象，其夫人、儿女、妹妹等面对镜头，讲述了他们心目中的邓小平。其中，邓小平与夫人卓琳心心相映、患难与共、相伴走过58个风云多变的春夏秋冬的故事尤为感人。");
        videoInfo10.setImageUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3347870091,3555500830&fm=26&gp=0.jpg");
        videoInfo4.setDetailUrl("https://vkceyugu.cdn.bspapp.com/VKCEYUGU-imgbed/4880a114-b71c-4ebd-ac58-2e3be423be1e.mp4");

        VideoInfo videoInfo11 = new VideoInfo();
        videoInfo11.setVideoName("永远的小平6");
        videoInfo11.setSummary("电视专题片《永远的小平》由凤凰卫视摄制，以邓小平的家人、身边工作人员作为主要的采访对象，其夫人、儿女、妹妹等面对镜头，讲述了他们心目中的邓小平。其中，邓小平与夫人卓琳心心相映、患难与共、相伴走过58个风云多变的春夏秋冬的故事尤为感人。");
        videoInfo11.setImageUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3347870091,3555500830&fm=26&gp=0.jpg");
        videoInfo4.setDetailUrl("https://vkceyugu.cdn.bspapp.com/VKCEYUGU-imgbed/4880a114-b71c-4ebd-ac58-2e3be423be1e.mp4");

        list.add(videoInfo1);
        list.add(videoInfo2);
        list.add(videoInfo3);
        list.add(videoInfo4);
        list.add(videoInfo5);
        list.add(videoInfo6);
        list.add(videoInfo7);
        list.add(videoInfo8);
        list.add(videoInfo9);
        list.add(videoInfo10);
        list.add(videoInfo11);

        List<String> coursewareName = new ArrayList<>();
        List<String> coursewareUrl = new ArrayList<>();
        coursewareName.add("第一章 毛泽东思想及其历史地位");
        coursewareName.add("第二章 新民主主义革命理论");
        coursewareName.add("第三章 社会主义改造理论");
        coursewareName.add("第四章 社会主义建设道路初步探索的理论成果");
        coursewareName.add("第五章 邓小平理论");
        coursewareName.add("第六章 三个代表”重要思想");
        coursewareName.add("第七章 科学发展观");
        coursewareName.add("第八章 习近平新时代中国特色社会主义思想及其历史地位");
        coursewareName.add("第九章 中国特色社会主义总任务 ");
        coursewareName.add("第十章 全面深化改革");
        coursewareName.add("第十一章 “五位一体”总体布局");
        coursewareName.add("第十二章 全面推进国防和军队现代化");
        coursewareName.add("第十三章“一国两制”与祖国统一");
        coursewareName.add("第十四章 中国特色大国外交");
        coursewareName.add("第十五章 全面从严治党");

        //1
        coursewareUrl.add("https://www.doc88.com/p-0083843848981.html");
        //2
        coursewareUrl.add("https://www.doc88.com/p-9758137017527.html");
        //3
        coursewareUrl.add("https://www.doc88.com/p-1324889511896.html");
        //4
        coursewareUrl.add("https://www.doc88.com/p-6009778666935.html");
        //5
        coursewareUrl.add("https://www.doc88.com/p-90099062365401.html");
        //6
        coursewareUrl.add("https://www.doc88.com/p-5435037021253.html");
        //7
        coursewareUrl.add("https://www.doc88.com/p-3989101965174.html");
        //8
        coursewareUrl.add("https://www.doc88.com/p-5146469854885.html");
        //9
        coursewareUrl.add("https://www.doc88.com/p-29016981004391.html");
        //10
        coursewareUrl.add("https://www.doc88.com/p-1753963384763.html");
        //11
        coursewareUrl.add("https://www.doc88.com/p-2176485359750.html");
        //12
        coursewareUrl.add("https://www.doc88.com/p-5199102744712.html");
        //13
        coursewareUrl.add("https://www.doc88.com/p-1167881489882.html");
        //14
        coursewareUrl.add("https://www.doc88.com/p-67747315485977.html");
        //15
        coursewareUrl.add("https://www.docin.com/p-2387409234.html");
        setCoursewareInfo(coursewareName, coursewareUrl);
    }

    public static List<VideoInfo> getAllVideoInfo() {
        return list;
    }

    public static List<Courseware> getCoursewares() {
        return coursewares;
    }

    public static List<VideoInfo> getVideoInfoList(int page) {
        List<VideoInfo> result = new ArrayList<>();
        int begin = page * FIVE;
        int end;
        if(begin + FIVE > list.size()) {
            end = list.size();
        } else {
            end = begin + FIVE;
        }
        for(int i = begin; i < end; ++i) {
            result.add(list.get(i));
        }
        return result;
    }

    /**
     * 两个List是相互对应的，对coursewares列表赋值
     * @param coursewareName 存放课件名List
     * @param coursewareUrl 存放课件URL LIst
     */
    public static void setCoursewareInfo(List<String> coursewareName, List<String> coursewareUrl){
        for(int i = 0; i < coursewareName.size(); i++){
            coursewares.add(new Courseware(i, coursewareName.get(i), coursewareUrl.get(i)));
        }
    }

}
