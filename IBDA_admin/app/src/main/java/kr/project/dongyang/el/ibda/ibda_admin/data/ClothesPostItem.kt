package kr.project.dongyang.el.ibda.ibda_admin.data

data class PostResult(
    var result:String? = null
)

data class ClothesPostItem(
    val category: String,
    val name: String,
    val image: String,
    val price: Int
)

