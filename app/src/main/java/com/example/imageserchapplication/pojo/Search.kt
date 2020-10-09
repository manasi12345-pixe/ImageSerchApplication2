package com.example.imageserchapplication.pojo

import java.io.Serializable

class Search : Serializable {
    lateinit var data: Array<Data>
    var success: String? = null
    var status: String? = null
    override fun toString(): String {
        return "ClassPojo [data = $data, success = $success, status = $status]"
    }

    inner class Processing {
        var status: String? = null
        override fun toString(): String {
            return "ClassPojo [status = $status]"
        }
    }

    class Images {
        var comment_count: String? = null
        var in_most_viral: String? = null
        var ad_type: String? = null
        var link: String? = null
        var description: String? = null
        var section: String? = null
        var title: String? = null
        var type: String? = null
        var points: String? = null
        var score: String? = null
        var datetime: String? = null
        var has_sound: String? = null
        var favorite_count: String? = null
        var id: String? = null
        var in_gallery: String? = null
        var vote: String? = null
        var views: String? = null
        var height: String? = null
        var downs: String? = null
        var bandwidth: String? = null
        var nsfw: String? = null
        var is_ad: String? = null
        var edited: String? = null
        var ad_url: String? = null
        lateinit var tags: Array<String>
        var account_id: String? = null
        var size: String? = null
        var width: String? = null
        var account_url: String? = null
        var animated: String? = null
        var ups: String? = null
        var favorite: String? = null
        override fun toString(): String {
            return "ClassPojo [comment_count = $comment_count, in_most_viral = $in_most_viral, ad_type = $ad_type, link = $link, description = $description, section = $section, title = $title, type = $type, points = $points, score = $score, datetime = $datetime, has_sound = $has_sound, favorite_count = $favorite_count, id = $id, in_gallery = $in_gallery, vote = $vote, views = $views, height = $height, downs = $downs, bandwidth = $bandwidth, nsfw = $nsfw, is_ad = $is_ad, edited = $edited, ad_url = $ad_url, tags = $tags, account_id = $account_id, size = $size, width = $width, account_url = $account_url, animated = $animated, ups = $ups, favorite = $favorite]"
        }
    }

    inner class Tags {
        var background_hash: String? = null
        var is_promoted: String? = null
        var background_is_animated: String? = null
        var thumbnail_hash: String? = null
        var description: String? = null
        var display_name: String? = null
        var is_whitelisted: String? = null
        var total_items: String? = null
        var accent: String? = null
        var thumbnail_is_animated: String? = null
        var followers: String? = null
        var following: String? = null
        var name: String? = null
        var logo_hash: String? = null
        var description_annotations: String? = null
        var logo_destination_url: String? = null
        override fun toString(): String {
            return "ClassPojo [background_hash = $background_hash, is_promoted = $is_promoted, background_is_animated = $background_is_animated, thumbnail_hash = $thumbnail_hash, description = $description, display_name = $display_name, is_whitelisted = $is_whitelisted, total_items = $total_items, accent = $accent, thumbnail_is_animated = $thumbnail_is_animated, followers = $followers, following = $following, name = $name, logo_hash = $logo_hash, description_annotations = $description_annotations, logo_destination_url = $logo_destination_url]"
        }
    }

    inner class Ad_config {
        var showsAds: String? = null
        lateinit var unsafeFlags: Array<String>
        lateinit var wallUnsafeFlags: Array<String>
        lateinit var safeFlags: Array<String>
        lateinit var highRiskFlags: Array<String>
        override fun toString(): String {
            return "ClassPojo [showsAds = $showsAds, unsafeFlags = $unsafeFlags, wallUnsafeFlags = $wallUnsafeFlags, safeFlags = $safeFlags, highRiskFlags = $highRiskFlags]"
        }
    }

    class Data {
        var comment_count: String? = null
        var in_most_viral: String? = null
        var ad_type: String? = null
        var link: String? = null
        var description: String? = null
        var privacy: String? = null
        var section: String? = null
        var cover_height: String? = null
        var title: String? = null
        var ad_config: Ad_config? = null
        var points: String? = null
        var cover: String? = null
        var score: String? = null
        var datetime: String? = null
        var favorite_count: String? = null
        var id: String? = null
        var topic_id: String? = null
        var in_gallery: String? = null
        var vote: String? = null
        var views: String? = null
        var include_album_ads: String? = null
        lateinit var images: Array<Images>
        var downs: String? = null
        var nsfw: String? = null
        var is_ad: String? = null
        var ad_url: String? = null
        var images_count: String? = null
        lateinit var tags: Array<Tags>
        var layout: String? = null
        var account_id: String? = null
        var cover_width: String? = null
        var is_album: String? = null
        var account_url: String? = null
        var ups: String? = null
        var topic: String? = null
        var favorite: String? = null
        override fun toString(): String {
            return "ClassPojo [comment_count = $comment_count, in_most_viral = $in_most_viral, ad_type = $ad_type, link = $link, description = $description, privacy = $privacy, section = $section, cover_height = $cover_height, title = $title, ad_config = $ad_config, points = $points, cover = $cover, score = $score, datetime = $datetime, favorite_count = $favorite_count, id = $id, topic_id = $topic_id, in_gallery = $in_gallery, vote = $vote, views = $views, include_album_ads = $include_album_ads, images = $images, downs = $downs, nsfw = $nsfw, is_ad = $is_ad, ad_url = $ad_url, images_count = $images_count, tags = $tags, layout = $layout, account_id = $account_id, cover_width = $cover_width, is_album = $is_album, account_url = $account_url, ups = $ups, topic = $topic, favorite = $favorite]"
        }
    }
}