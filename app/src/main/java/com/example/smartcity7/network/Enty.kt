package com.example.smartcity7.network

data class Login(
    val password: String,
    val username: String
)

data class Message(
    val code: Int,
    val msg: String,
    val token: String
)

data class HomeBanner(
    val code: String,
    val msg: String,
    val rows: List<Row>,
    val total: String
) {
    data class Row(
        val advImg: String,
        val advTitle: String,
        val id: Int,
        val servModule: String,
        val sort: Int,
        val targetId: Int,
        val type: String
    )
}

data class HomeSerive(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val id: Int,
        val imgUrl: String,
        val isRecommend: String,
        val link: String,
        val serviceDesc: String,
        val serviceName: String,
        val serviceType: String,
        val sort: Int
    )
}

data class NewsType(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
) {
    data class Data(
        val appType: String,
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val name: String,
        val params: Params,
        val parentId: Any,
        val remark: Any,
        val searchValue: Any,
        val sort: Int,
        val status: String,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class NewsList(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val appType: String,
        val commentNum: Int,
        val content: String,
        val cover: String,
        val createBy: String,
        val createTime: String,
        val hot: String,
        val id: Int,
        val likeNum: Int,
        val params: Params,
        val publishDate: String,
        val readNum: Int,
        val remark: Any,
        val searchValue: Any,
        val status: String,
        val subTitle: Any,
        val tags: Any,
        val title: String,
        val top: String,
        val type: String,
        val updateBy: String,
        val updateTime: String
    ) {
        class Params
    }
}
data class NewsContent(
    val code: Int,
    val `data`: Data,
    val msg: String
) {
    data class Data(
        val appType: String,
        val commentNum: Int,
        val content: String,
        val cover: String,
        val createBy: String,
        val createTime: String,
        val hot: String,
        val id: Int,
        val likeNum: Int,
        val params: Params,
        val publishDate: String,
        val readNum: Int,
        val remark: Any,
        val searchValue: Any,
        val status: String,
        val subTitle: Any,
        val tags: Any,
        val title: String,
        val top: String,
        val type: String,
        val updateBy: String,
        val updateTime: String
    ) {
        class Params
    }
}

data class UserInfo(
    val code: Int,
    val msg: String,
    val user: User
) {
    data class User(
        val avatar: String,
        val balance: Int,
        val email: String,
        val idCard: String,
        val nickName: String,
        val phonenumber: String,
        val score: Int,
        val sex: String,
        val userId: Int,
        val userName: String
    )
}

data class User(
    val nickName: String,
    val phonenumber: String,
    val sex: String,
    val avatar: String
)

data class Pass(
    val newPassword: String,
    val oldPassword: String
)

data class Feed(
    val content: String,
    val title: String
)

data class ImageUpload(
    val code: Int,
    val fileName: String,
    val msg: String,
    val url: String
)

data class PetType(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
) {
    data class Data(
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val imgUrl: String,
        val name: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class MyVisList(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val createBy: Any,
        val createTime: String,
        val doctor: Doctor?,
        val doctorId: Int,
        val id: Int,
        val imageUrls: String,
        val params: Params,
        val question: String,
        val remark: Any,
        val searchValue: Any,
        val updateBy: Any,
        val updateTime: Any,
        val userId: Int
    ) {
        data class Doctor(
            val avatar: String,
            val createBy: Any,
            val createTime: Any,
            val goodAt: String,
            val id: Int,
            val jobName: String,
            val name: String,
            val params: Params,
            val practiceNo: String,
            val remark: Any,
            val searchValue: Any,
            val typeId: Int,
            val updateBy: Any,
            val updateTime: Any,
            val workingYears: Int
        ) {
            class Params
        }

        class Params
    }
}

data class Inquiry(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val createBy: Any,
        val createTime: String,
        val doctor: Doctor,
        val doctorId: Int,
        val id: Int,
        val imageUrls: String,
        val params: Params,
        val question: String,
        val remark: Any,
        val searchValue: Any,
        val updateBy: Any,
        val updateTime: Any,
        val userId: Int
    ) {
        data class Doctor(
            val avatar: String,
            val createBy: Any,
            val createTime: Any,
            val goodAt: String,
            val id: Int,
            val jobName: String,
            val name: String,
            val params: Params,
            val practiceNo: String,
            val remark: Any,
            val searchValue: Any,
            val typeId: Int,
            val updateBy: Any,
            val updateTime: Any,
            val workingYears: Int
        ) {
            class Params
        }
        class Params
    }
}

data class Doctor(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val avatar: String,
        val createBy: Any,
        val createTime: Any,
        val goodAt: String,
        val id: Int,
        val jobName: String,
        val name: String,
        val params: Params,
        val practiceNo: String,
        val remark: Any,
        val searchValue: Any,
        val typeId: Int,
        val updateBy: Any,
        val updateTime: Any,
        val workingYears: Int
    ) {
        class Params
    }
}

data class In(
    val doctorId: Int,
    val imageUrls: String,
    val question: String
)

data class MetroList(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
) {
    data class Data(
        val currentName: String,
        val lineId: Int,
        val lineName: String,
        val nextStep: NextStep,
        val preStep: PreStep,
        val reachTime: Int
    ) {
        data class NextStep(
            val lines: List<Line>,
            val name: String
        ) {
            data class Line(
                val lineId: Int,
                val lineName: String
            )
        }

        data class PreStep(
            val lines: List<Line>,
            val name: String
        ) {
            data class Line(
                val lineId: Int,
                val lineName: String
            )
        }
    }
}

data class PartContent(
    val code: Int,
    val `data`: Data,
    val msg: String
) {
    data class Data(
        val cityId: Int,
        val end: String,
        val endTime: String,
        val first: String,
        val id: Int,
        val km: Int,
        val metroStepList: List<MetroStep>,
        val name: String,
        val remainingTime: Int,
        val runStationsName: String,
        val startTime: String,
        val stationsNumber: Int
    ) {
        data class MetroStep(
            val createBy: Any,
            val createTime: String,
            val firstCh: String,
            val id: Int,
            val lineId: Int,
            val name: String,
            val params: Params,
            val remark: Any,
            val searchValue: Any,
            val seq: Int,
            val updateBy: Any,
            val updateTime: String
        ) {
            class Params
        }
    }
}

data class LogBanner(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
) {
    data class Data(
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val imgUrl: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val sort: Int,
        val title: String,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class LogList(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
) {
    data class Data(
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val imgUrl: String,
        val introduce: String,
        val name: String,
        val newsList: Any,
        val params: Params,
        val phone: String,
        val priceList: Any,
        val remark: Any,
        val searchValue: Any,
        val shippingMethod: String,
        val sort: Int,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class LogContent(
    val code: Int,
    val `data`: Data,
    val msg: String
) {
    data class Data(
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val imgUrl: String,
        val introduce: String,
        val name: String,
        val newsList: List<Any>,
        val params: Params,
        val phone: String,
        val priceList: List<Any>,
        val remark: Any,
        val searchValue: Any,
        val shippingMethod: String,
        val sort: Int,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class LogInfo(
    val code: Int,
    val `data`: Data,
    val msg: String
) {
    data class Data(
        val company: Company,
        val companyId: Int,
        val createBy: Any,
        val createTime: String,
        val id: Int,
        val infoNo: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val stepList: List<Step>,
        val updateBy: Any,
        val updateTime: Any
    ) {
        data class Company(
            val createBy: Any,
            val createTime: Any,
            val id: Int,
            val imgUrl: String,
            val introduce: String,
            val name: String,
            val newsList: Any,
            val params: Params,
            val phone: String,
            val priceList: Any,
            val remark: Any,
            val searchValue: Any,
            val shippingMethod: String,
            val sort: Int,
            val updateBy: Any,
            val updateTime: Any
        ) {
            class Params
        }

        class Params

        data class Step(
            val addressAt: String,
            val createBy: Any,
            val createTime: Any,
            val eventAt: String,
            val id: Int,
            val infoId: Int,
            val params: Params,
            val remark: Any,
            val searchValue: Any,
            val stateName: String,
            val updateBy: Any,
            val updateTime: Any
        ) {
            class Params
        }
    }
}