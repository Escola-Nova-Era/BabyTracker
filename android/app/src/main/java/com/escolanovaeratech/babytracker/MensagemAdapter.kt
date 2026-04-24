package com.escolanovaeratech.babytracker

import android.content.res.Resources
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class MensagemAdapter(
    private val items: List<TimelineItem>
) : RecyclerView.Adapter<MensagemAdapter.MensagemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensagemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)
        return MensagemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MensagemViewHolder, position: Int) {
        holder.bind(items[position], position == items.lastIndex)
    }

    override fun getItemCount(): Int = items.size

    inner class MensagemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val line: View = itemView.findViewById(R.id.timelineLine)
        private val iconBubble: TextView = itemView.findViewById(R.id.iconBubble)
        private val dot: View = itemView.findViewById(R.id.timelineDot)
        private val card: MaterialCardView = itemView.findViewById(R.id.cardContainer)
        private val title: TextView = itemView.findViewById(R.id.tvTitle)
        private val time: TextView = itemView.findViewById(R.id.tvTime)
        private val subtitle: TextView = itemView.findViewById(R.id.tvSubtitle)
        private val metaContainer: View = itemView.findViewById(R.id.metaContainer)
        private val metaPrimary: TextView = itemView.findViewById(R.id.tvMetaPrimary)
        private val metaSecondary: TextView = itemView.findViewById(R.id.tvMetaSecondary)
        private val tag: TextView = itemView.findViewById(R.id.tvTag)

        fun bind(item: TimelineItem, isLastItem: Boolean) {
            val context = itemView.context

            title.text = item.title
            time.text = item.time
            iconBubble.text = item.icon
            line.visibility = if (isLastItem) View.INVISIBLE else View.VISIBLE

            if (item.subtitle != null) {
                subtitle.visibility = View.VISIBLE
                subtitle.text = item.subtitle
            } else {
                subtitle.visibility = View.GONE
            }

            if (item.tag != null) {
                tag.visibility = View.VISIBLE
                tag.text = item.tag
                tag.background = roundedDrawable(
                    ContextCompat.getColor(context, item.tagBackgroundColor),
                    999f
                )
                tag.setTextColor(ContextCompat.getColor(context, item.tagTextColor))
            } else {
                tag.visibility = View.GONE
            }

            if (item.metaPrimary != null || item.metaSecondary != null) {
                metaContainer.visibility = View.VISIBLE

                if (item.metaPrimary != null) {
                    metaPrimary.visibility = View.VISIBLE
                    metaPrimary.text = item.metaPrimary
                } else {
                    metaPrimary.visibility = View.GONE
                }

                if (item.metaSecondary != null) {
                    metaSecondary.visibility = View.VISIBLE
                    metaSecondary.text = item.metaSecondary
                } else {
                    metaSecondary.visibility = View.GONE
                }
            } else {
                metaContainer.visibility = View.GONE
            }

            iconBubble.background = roundedDrawable(
                ContextCompat.getColor(context, item.bubbleColor),
                14f
            )
            dot.background = roundedDrawable(
                ContextCompat.getColor(context, item.dotColor),
                999f
            )
            card.strokeColor = ContextCompat.getColor(context, R.color.card_stroke)
        }
    }

    private fun roundedDrawable(color: Int, radiusDp: Float): GradientDrawable {
        return GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = radiusDp * density
            setColor(color)
        }
    }

    private val density: Float
        get() = Resources.getSystem().displayMetrics.density
}

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
