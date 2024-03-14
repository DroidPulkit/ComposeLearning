package com.example.firstcompose

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcompose.dto.ListItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(Modifier.verticalScroll(ScrollState(0))) {
                //Learn how to call dynamic text
                SayName("Yellow")
                SayName("Bruh")
                //Learn how to open new activity and also how to add compose to normal XML
                OpenAnotherActivity(this@MainActivity)
                //Learn how to add images
                MakeImage()
                //Learn how to add button with image + text
                ButtonTextAndImage()
                //Learn the state management concept via TextField
                WorkingTextFieldWithStateManagement()
                //Column - one below another
                UseOfColumn()
                //Row - In 1 line
                UseOfRow()
                //FrameLayout
                UseOfBox()
                //Learning Recyclerview type items design
                ListViewItem(
                    ListItem(
                        R.drawable.toronto,
                        "Some Image",
                        "Joe Matthews",
                        "Content Writer")
                )
                ListViewItem(
                    ListItem(
                        R.drawable.toronto,
                        "Some Image",
                        "Lilly Smith",
                        "HR")
                )
                ListViewItem(
                    ListItem(
                        R.drawable.toronto,
                        "Some Image",
                        "Alisha Kwok",
                        "Staff Engineer")
                )
                ListViewItem(
                    ListItem(
                        R.drawable.toronto,
                        "Some Image",
                        "Brian Lara",
                        "Director of IT")
                )
                SayName("Yellow")
                MakeCircularImageUsingModifiers()
            }
        }
    }
}

@Preview(showBackground = true, name = "test1", widthDp = 400, heightDp = 200)
@Composable
fun SayName(name: String = "Pulkit") {
    Text(text = "Hello $name")
}

@Preview(showBackground = true, name = "image1", widthDp = 400, heightDp = 200)
@Composable
fun MakeImage() {
    Image(
        painter = painterResource(id = R.drawable.abc),
        contentDescription = "Dummy Image",
        colorFilter = ColorFilter.tint(Color.Magenta, BlendMode.Overlay),
        contentScale = ContentScale.Inside
    )
}

@Preview(showBackground = true, name = "test2", widthDp = 400, heightDp = 200)
@Composable
fun SayName2(name: String = "Pulkit") {
    Text(
        text = "Hello $name",
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.ExtraBold,
        color = Color.Magenta,
        fontSize = 40.sp,
        textAlign = TextAlign.Right
    )
}

@Composable
fun OpenAnotherActivity(activity: Activity?) {
    Button(onClick = {
        activity?.startActivity(Intent(activity, TestXMLActivity::class.java))
    }) {
        Text(text = "Open Another Activity")
    }
}

@Preview(showBackground = true, name = "TextImageButton", widthDp = 300, heightDp = 100)
@Composable
fun ButtonTextAndImage() {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Gray
        ),
        shape = ButtonDefaults.outlinedShape
    ) {
        Text(text = "Hello")
        Image(
            painter = painterResource(id = R.drawable.toronto),
            contentDescription = "Toronto",
        )
    }
}

@Preview
@Composable
fun TextFieldTest() {
    TextField(
        value = "Hello",
        onValueChange = {},
        label = { Text(text = "label") },
        placeholder = { Text(text = "placeholder") })

}

@Preview
@Composable
fun WorkingTextFieldWithStateManagement()
{
    val state = remember {
        mutableStateOf("")
    }
    TextField(value = state.value,
        onValueChange = { state.value = it },
        placeholder = { Text(text = "Placeholder")},
        label = { Text(text = "Label")})
}

@Preview(showBackground = true, widthDp = 400, heightDp = 400)
@Composable
fun UseOfColumn()
{
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Some text")
        Text(text = "Another text")
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 400)
@Composable
fun UseOfRow()
{
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = "Test 1")
        Text(text = "Test 2")
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 400)
@Composable
fun UseOfBox()
{
    //This is like frameLayout
    Box(contentAlignment = Alignment.Center) {
        Text(text = "Test 1")
    }
}

@Composable
fun ListViewItem(listItem: ListItem)
{
    Row(Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = listItem.avatar),
            contentDescription = listItem.avatarContentDescription,
            Modifier.size(60.dp))
        Column {
            Text(text = listItem.name, fontWeight = FontWeight.Bold)
            Text(text = listItem.occupation)
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 400)
@Composable
fun PreviewListViewItem() {
    Column {
        ListViewItem(
            ListItem(
                R.drawable.toronto,
                "Some Image",
                "Joe Matthews",
                "Content Writer")
        )
        ListViewItem(
            ListItem(
                R.drawable.toronto,
                "Some Image",
                "Lilly Smith",
                "HR")
        )
        ListViewItem(
            ListItem(
                R.drawable.toronto,
                "Some Image",
                "Alisha Kwok",
                "Staff Engineer")
        )
        ListViewItem(
            ListItem(
                R.drawable.toronto,
                "Some Image",
                "Brian Lara",
                "Director of IT")
        )
    }
}

@Preview(showBackground = true, widthDp = 800, heightDp = 800)
@Composable
fun MakeCircularImageUsingModifiers()
{
    Image(
        painter = painterResource(id = R.drawable.abc),
        contentDescription = "Some desc",
        modifier = Modifier.size(290.dp)
            .padding(10.dp)
            .clip(CircleShape)
            .border(5.dp, Color.Red, CircleShape)
    )
}

@Preview
@Composable
fun PreviewOpenAnotherActivity() {
    OpenAnotherActivity(activity = null)
}