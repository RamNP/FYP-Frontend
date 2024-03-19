//package com.edu.vplayer.features.data.common
//
//import androidx.room.TypeConverter
//import com.edu.vplayer.features.data.resource.remote.api.model.ChaptersItem
//import com.edu.vplayer.features.data.resource.remote.api.model.TopicsItem
//import com.edu.vplayer.features.data.resource.remote.api.model.VideosItem
//import com.google.common.reflect.TypeToken
//import com.google.gson.Gson
//
//class ChaptersTypeConverter {
//    @TypeConverter
//    fun fromChaptersItem(chaptersItem: List<ChaptersItem?>?): String? {
//        return Gson().toJson(chaptersItem)
//    }
//    @TypeConverter
//    fun toChaptersItem(chaptersItemJson: String?):  List<ChaptersItem?>? {
//        return  Gson().fromJson(chaptersItemJson, object : TypeToken<List<ChaptersItem?>>(){}.type )
//    }
//
//    @TypeConverter
//    fun fromTopicsItem(topicsItem: List<TopicsItem?>?): String? {
//        return Gson().toJson(topicsItem)
//    }
//    @TypeConverter
//    fun toTopicsItem(topicsItemJson: String?):  List<TopicsItem?>? {
//        return  Gson().fromJson(topicsItemJson, object : TypeToken<List<TopicsItem?>>(){}.type )
//    }
//    @TypeConverter
//    fun fromVideosItem(videosItem: List<VideosItem?>?): String? {
//        return Gson().toJson(videosItem)
//    }
//    @TypeConverter
//    fun toVideosItem(videosItemJson: String?):  List<VideosItem?>? {
//        return  Gson().fromJson(videosItemJson, object : TypeToken<List<VideosItem?>>(){}.type )
//    }
//    @TypeConverter
//    fun fromStrings(string: List<String?>?): String? {
//        return Gson().toJson(string)
//    }
//    @TypeConverter
//    fun toStrings(stringJson: String?):  List<String?>? {
//        return  Gson().fromJson(stringJson, object : TypeToken<List<String?>>(){}.type )
//    }
//
//}
//
//
