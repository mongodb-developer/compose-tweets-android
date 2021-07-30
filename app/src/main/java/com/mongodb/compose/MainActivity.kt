package com.mongodb.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mongodb.compose.ui.theme.ComposeDeleteItTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDeleteItTheme {
                // A surface container using the 'background' color from the theme
                homePageContent()
            }
        }
    }
}

@Composable
fun homePageContent(mainViewModel: MainViewModel = viewModel()) {

    val tweetDetails by mainViewModel.lastTweet.observeAsState()

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column {
            getAppTopBar()
            userProfileView(
                userName = tweetDetails!!.authorName,
                userHandle = tweetDetails!!.handle
            )
            tweetContent(tweetDetails!!.tweetContent)
            divider()
            tweetTimestamp(
                time = tweetDetails!!.formatTime(),
                date = tweetDetails!!.formatDate()
            )
            divider()
            tweetReachInfoBlock()
            divider()
            tweetActionBlock()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun divider() {
    Divider(color = Color.LightGray, thickness = 0.5.dp)
}


@Preview
@Composable
fun homePageContentPreview() {

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column {
            getAppTopBar()
            userProfilePreview()
            tweetContentPreview()
            divider()
            tweetTimestampPreview()
            divider()
            tweetReachInfoBlock()
            divider()
            tweetActionBlock()
        }
    }
}

@Preview
@Composable
fun getAppTopBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        elevation = 0.dp
    )
}

@Preview(showBackground = true)
@Composable
fun userProfilePreview() {
    userProfileView(userName = "Mohit Sharma", userHandle = "@codeWithMohit")
}

@Composable
fun userProfileView(userName: String, userHandle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Transparent, CircleShape),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(start = 12.dp)
        ) {
            Text(text = userName, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(
                text = userHandle,
                fontSize = 12.sp,
                color = colorResource(id = R.color.shade_grey)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun tweetContentPreview() {
    val defaultContent = "The production-ready version of compose is here.\n" +
            "#JetpackCompose"
    tweetContent(content = defaultContent)
}

@Composable
fun tweetContent(content: String) {
    Text(
        text = content,
        modifier = Modifier
            .padding(12.dp)
            .wrapContentHeight()
            .defaultMinSize(minHeight = 150.dp),
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium
    )
}

@Preview(showBackground = true)
@Composable
fun tweetTimestampPreview() {
    tweetTimestamp("11:30 AM ", "12 Mar 2021")
}

@Composable
fun tweetTimestamp(time: String, date: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append(time)
                }
                append(" • ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append(date)
                }
            },
            modifier = Modifier.padding(end = 8.dp),
            color = colorResource(id = R.color.shade_grey)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun tweetReachInfoBlock() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                    append("3")
                }
                append(" ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.shade_grey)
                    )
                ) {

                    append(stringResource(id = R.string.retweets))
                }
            },
            modifier = Modifier.padding(end = 24.dp)
        )

        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                append("3")
            }
            append(" ")
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.shade_grey)
                )
            ) {
                append(stringResource(id = R.string.likes))
            }
        })

    }
}

@Preview(showBackground = true)
@Composable
fun tweetActionBlock() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(painter = painterResource(id = R.drawable.ic_comment), contentDescription = "")

        Image(painter = painterResource(id = R.drawable.ic_retweet), contentDescription = "")

        Image(
            painter = painterResource(id = R.drawable.ic_favorite_outline),
            contentDescription = ""
        )

        Image(painter = painterResource(id = R.drawable.ic_share), contentDescription = "")
    }
}



