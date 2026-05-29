
import android.view.ContextThemeWrapper
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.escolanovaeratech.babytracker.MensagemAdapter
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.TimelineItem

@Composable
fun TimelineScreen() {
    AndroidView(
        factory = {
            val themedContext = ContextThemeWrapper(
                it,
                R.style.RecyclerViewCompose
            )
            RecyclerView(themedContext).apply {

                layoutManager = LinearLayoutManager(themedContext)

                adapter = MensagemAdapter(
                    buildTimeline()
                )
            }
        }
    )
}

private fun buildTimeline(): List<TimelineItem> {
    return listOf(
        TimelineItem(
            title = "Bottle Feeding",
            time = "2:30 PM",
            icon = "\uD83C\uDF7C",
            bubbleColor = R.color.feed_bubble,
            dotColor = R.color.feed_dot,
            metaPrimary = "\u2195 120 ml",
            metaSecondary = "\u25F4 15 min"
        ),
        TimelineItem(
            title = "Woke Up",
            time = "2:00 PM",
            icon = "\u2600",
            bubbleColor = R.color.sleep_bubble,
            dotColor = R.color.sleep_dot,
            subtitle = "Slept for 2h 15min"
        ),
        TimelineItem(
            title = "Diaper Change",
            time = "1:45 PM",
            icon = "\uD83D\uDC76",
            bubbleColor = R.color.diaper_bubble,
            dotColor = R.color.diaper_dot,
            tag = "Pee",
            tagBackgroundColor = R.color.tag_blue_bg,
            tagTextColor = R.color.tag_blue_text
        ),
        TimelineItem(
            title = "Fell Asleep",
            time = "11:45 AM",
            icon = "\u263E",
            bubbleColor = R.color.sleep_bubble,
            dotColor = R.color.sleep_dot,
            subtitle = "Nap time"
        ),
        TimelineItem(
            title = "Breastfeeding",
            time = "11:15 AM",
            icon = "\u2665",
            bubbleColor = R.color.breast_bubble,
            dotColor = R.color.breast_dot,
            metaPrimary = "Left side",
            metaSecondary = "\u25F4 20 min"
        ),
        TimelineItem(
            title = "Bath Time",
            time = "10:00 AM",
            icon = "\uD83D\uDEC1",
            bubbleColor = R.color.bath_bubble,
            dotColor = R.color.bath_dot,
            subtitle = "Warm bath"
        ),
        TimelineItem(
            title = "Diaper Change",
            time = "9:30 AM",
            icon = "\uD83D\uDC76",
            bubbleColor = R.color.diaper_bubble,
            dotColor = R.color.diaper_dot,
            tag = "Poop"
        )
    )
}