package com.escolanovaeratech.babytracker.timeline.data

import com.escolanovaeratech.babytracker.R

data class TimelineItem(
    val title: String,
    val time: String,
    val icon: String,
    val bubbleColor: Int,
    val dotColor: Int,
    val subtitle: String? = null,
    val metaPrimary: String? = null,
    val metaSecondary: String? = null,
    val tag: String? = null,
    val tagBackgroundColor: Int = R.color.tag_yellow_bg,
    val tagTextColor: Int = R.color.tag_yellow_text
)
