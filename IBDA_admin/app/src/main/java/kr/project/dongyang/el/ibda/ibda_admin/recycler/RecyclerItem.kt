package graduation.project.dongyang.el.ibda.recycler

data class ClothesResponseItem(
    val category: String,
    val id : String,
    val image: String,
    val name: String,
    val price: String
)

data class ClothesHardcodingItem(
    val id : Int,
    val category: String,
    val image: Int,
    val name: String,
    val price: Int
)

data class MemberHardcodingItem(
    val id : Int,
    val account : String,
    val name: String,
    val age : Int,
    val level : String
)
