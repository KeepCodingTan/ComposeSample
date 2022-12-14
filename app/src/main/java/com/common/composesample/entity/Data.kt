package com.common.composesample.entity

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.common.composesample.R
import com.common.composesample.widget.BottomData
import java.util.*

/**
 * @Author: Sun
 * @CreateDate: 2022/12/3
 * @Description: java类作用描述
 */
private const val DEFAULT_IMAGE_WIDTH = "250"

data class TodoItem(
    val task: String,
    val icon: ToDoIcon = ToDoIcon.Default,
    val id: UUID = UUID.randomUUID()
)

enum class ToDoIcon(
   val imageVector: ImageVector,
   @StringRes val contentDescription: Int
){
    Square(Icons.Default.CropSquare, R.string.cd_expand),
    Done(Icons.Default.Done, R.string.cd_expand),
    Event(Icons.Default.Event, R.string.cd_event),
    Privacy(Icons.Default.PrivacyTip, R.string.cd_privacy),
    Trash(Icons.Default.RestoreFromTrash, R.string.cd_restore);

    companion object{
        val Default = Square
    }
}

val items = listOf(
    BottomData("首页", R.mipmap.tab_home, R.mipmap.tab_home_no),
    BottomData("选课", R.mipmap.tab_select_course, R.mipmap.tab_select_course_no),
    BottomData("学习", R.mipmap.tab_study, R.mipmap.tab_study_no),
    BottomData("题库", R.mipmap.tab_my_exam, R.mipmap.tab_my_exam_no),
    BottomData("我的", R.mipmap.tab_my, R.mipmap.tab_my_no)
)

val images = listOf(
    "https://images.unsplash.com/photo-1534308983496-4fabb1a015ee?ixlib=rb-1.2.1&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1495749388945-9d6e4e5b67b1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1562625964-ffe9b2f617fc?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=250&q=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1515443961218-a51367888e4b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/flagged/photo-1556202256-af2687079e51?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1542384557-0824d90731ee?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1567337710282-00832b415979?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1445019980597-93fa8acb246c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1570213489059-0aac6626cade?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1561409037-c7be81613c1f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1455587734955-081b22074882?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/46/sh3y2u5PSaKq8c4LxB3B_submission-photo-4.jpg?ixlib=rb-1.2.1&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1570214476695-19bd467e6f7a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1544735716-392fe2489ffa?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1539037116277-4db20889f2d4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1518548419970-58e3b4079ab2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1515542622106-78bda8ba0e5b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1534423839368-1796a4dd1845?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1544550581-5f7ceaf7f992?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1557160854-e1e89fdd3286?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1562883676-8c7feb83f09b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1486575008575-27670acb58db?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1534308983496-4fabb1a015ee?ixlib=rb-1.2.1&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
)

val banners = listOf(
    "https://images.unsplash.com/photo-1534423839368-1796a4dd1845?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1544550581-5f7ceaf7f992?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1557160854-e1e89fdd3286?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1562883676-8c7feb83f09b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
    "https://images.unsplash.com/photo-1486575008575-27670acb58db?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH",
)

val news = listOf(
    News("马斯克短暂失去全球首富头衔"),
    News("在南京大屠杀惨案发生85周年之际，举国上下同悼死难同胞，是哀悼更是警醒，历史悲剧决不能重演，我辈当勠力同心，为中华民族的伟大复兴不懈奋斗。"),
    News("12月12日0—24时，31个省（自治区、直辖市）和新疆生产建设兵团报告新增本土确诊病例2270例，本土无症状感染5181例。"),
    News("多喝水多睡觉对新冠管用吗？对此有专家表示，喝水因人而异，要适量喝水。多睡觉非常有必要，多休息才能让身体处于最好的状态去对抗疾病。"),
    News("12日，据外媒报道，富力集团联席董事长张力因涉嫌行贿在伦敦被逮捕。富力集团表声明称，张力被指控涉嫌行贿，己方正针对此错误指控采取法律行动。"),
    News("近期，多地取消大规模核酸，这种情况下，预估感染人数变得困难。对此，有专家称，可通过两个途径了解：一是自测抗原后上报，二是对当地污水进行监测。"),
    News("在卡塔尔世界杯半决赛中，官方宣布《孤勇者》、《相信》两首中文歌曲将在半决赛现场播放。"),
    News("《福布斯》的最新数据显示，特斯拉CEO埃隆·马斯克已经丢掉世界首富宝座，被路易·威登母公司LVMH董事长伯纳德·阿尔诺超越。"),
    News("“奥密克戎重复感染的概率大吗”是很多人关心的问题，12日，李兰娟回应称，从目前的研究数据看，感染一次奥密克戎后短时间内再次感染的概率较低。 "),
    News("12月11日，河南周口赵女士新冠阳性居家隔离，老公来送水用塑料袋套头，边走边喷酒精。赵女士称这是她确诊第四天，老公悉心照料希望自己早点康复。"),
    News("近日，“宝娟我的嗓子”火了。不少网友称得了新冠后，嗓子真的很像安陵容。在患者出现新冠肺炎引起咽痛的病情发作时，可以使用抗病毒类药物治疗。 "),
    News("2022年是南京大屠杀惨案发生85周年。12月13日上午10时，我国在侵华日军南京大屠杀遇难同胞纪念馆集会广场举行国家公祭仪式。"),
    News("12月12日，对于如何做好阳性者的居家健康管理和日常预防，郑州市疾控中心流行病学首席专家李肖红表示，建议阳性者非必要尽量少叫快递外卖。"),
    News("9日，广东湛江一女子被男子连捅数刀后死亡，10日该男子投案自首，并交代作案是因“感情纠纷”。但受害人姐姐表示，妹妹跟他没有感情上的关系。"),
    News("11日晚，胡锡进跟拍被打爆的北京120，希望每个人最需要时，救护车都能第一时间赶到。"),
)

val searchHistory = listOf(
    "马斯克短暂失去全球首富头衔",
    "新冠症状",
    "为什么很强是牛批",
    "牛批是什么意思",
    "有点东西",
    "搜索了啥呢",
)

data class News(
    val title: String,
    val source: String = "来源：人民日报",
    val time: String = "2022-12-12"
)
