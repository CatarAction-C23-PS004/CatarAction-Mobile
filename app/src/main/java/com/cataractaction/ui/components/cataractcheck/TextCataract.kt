package com.cataractaction.ui.components.cataractcheck

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextCataract() {
    Text(
        text = "*Image must contain 1 eye",
        modifier = Modifier.padding(start = 34.dp),
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.onError
    )
    Spacer(Modifier.size(10.dp))
    Text(
        text = "Select Image from",
        modifier = Modifier.padding(start = 34.dp),
        style = MaterialTheme.typography.body2.copy(fontSize = 16.sp)
    )
}


@Composable
fun TextAnalysis() {
    Text(
        text = "Analysis Result",
        modifier = Modifier.padding(start = 34.dp),
        style = MaterialTheme.typography.body2.copy(fontSize = 16.sp)
    )
}

@Composable
fun TextResult(result: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Result :",
            modifier = Modifier.padding(start = 34.dp),
            style = MaterialTheme.typography.body1.copy(fontSize = 12.sp)
        )
        Spacer(Modifier.size(5.dp))
        Text(
            text = if (result == "1") "Cataract" else "Normal",
            color = MaterialTheme.colors.onError,
            style = MaterialTheme.typography.body1.copy(fontSize = 12.sp)
        )
    }
}

@Composable
fun TextResult2(result: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Result :",
            modifier = Modifier.padding(start = 34.dp),
            style = MaterialTheme.typography.body1.copy(fontSize = 12.sp)
        )
        Spacer(Modifier.size(5.dp))
        Text(
            text = if (result == "Cataract") "Cataract" else "Normal",
            color = MaterialTheme.colors.onError,
            style = MaterialTheme.typography.body1.copy(fontSize = 12.sp)
        )
    }
}


@Composable
fun TextDesc(result: String) {
    Row(modifier = Modifier.padding(horizontal = 34.dp)) {
        Text(
            text = "Description :",
            style = MaterialTheme.typography.body1.copy(fontSize = 12.sp)
        )
        Spacer(Modifier.size(5.dp))
        Text(
            text = if (result == "Cataract") "Please, schedule an appointment with an eye care professional for a comprehensive eye examination as soon as possible." else "Here are some key practices to consider for optimal eye health :  \n\n" +
                    "1.Eating a balanced diet rich in fruits, vegetables, and nutrients like omega-3 fatty acids, vitamins C and E, zinc, and lutein can promote good eye health.\n\n" +
                    "2. Protect your eyes from harmful UV rays by wearing sunglasses with 100% UV protection and using wide-brimmed hats when outdoors.\n\n" +
                    "3. Taking breaks and practicing the 20-20-20 rule (looking at something 20 feet away for 20 seconds every 20 minutes) can reduce eye strain during prolonged screen use.\n\n" +
                    "4. Ensure proper lighting and ergonomics in your workspace to minimize eye fatigue and discomfort.\n\n" +
                    "5. Practice good hygiene by washing your hands before touching your eyes or handling contact lenses to prevent infections.\n\n" +
                    "6. Avoid smoking, as it increases the risk of developing eye conditions such as cataracts and macular degeneration.\n\n" +
                    "7. Limit screen time and give your eyes regular rest to prevent digital eye strain.\n\n" +
                    "8. Stay hydrated by drinking an adequate amount of water daily to prevent dry eyes.\n\n" +
                    "9.Use protective eyewear during activities that pose a risk of eye injury, such as playing sports or working in hazard ous environments.",
            color = MaterialTheme.colors.onError,
            style = MaterialTheme.typography.body1.copy(fontSize = 12.sp)
        )
    }
}

@Composable
fun TextDescResult(result: String) {
    Row(modifier = Modifier.padding(horizontal = 34.dp)) {
        Text(
            text = "Description :",
            style = MaterialTheme.typography.body1.copy(fontSize = 12.sp)
        )
        Spacer(Modifier.size(5.dp))
        Text(
            text = if (result == "Cataract") "Please, schedule an appointment with an eye care professional for a comprehensive eye examination as soon as possible." else "Here are some key practices to consider for optimal eye health :  \n\n" +
                    "1.Eating a balanced diet rich in fruits, vegetables, and nutrients like omega-3 fatty acids, vitamins C and E, zinc, and lutein can promote good eye health.\n\n" +
                    "2. Protect your eyes from harmful UV rays by wearing sunglasses with 100% UV protection and using wide-brimmed hats when outdoors.\n\n" +
                    "3. Taking breaks and practicing the 20-20-20 rule (looking at something 20 feet away for 20 seconds every 20 minutes) can reduce eye strain during prolonged screen use.\n\n" +
                    "4. Ensure proper lighting and ergonomics in your workspace to minimize eye fatigue and discomfort.\n\n" +
                    "5. Practice good hygiene by washing your hands before touching your eyes or handling contact lenses to prevent infections.\n\n" +
                    "6. Avoid smoking, as it increases the risk of developing eye conditions such as cataracts and macular degeneration.\n\n" +
                    "7. Limit screen time and give your eyes regular rest to prevent digital eye strain.\n\n" +
                    "8. Stay hydrated by drinking an adequate amount of water daily to prevent dry eyes.\n\n" +
                    "9.Use protective eyewear during activities that pose a risk of eye injury, such as playing sports or working in hazard ous environments.",
            color = MaterialTheme.colors.onError,
            style = MaterialTheme.typography.body1.copy(fontSize = 12.sp)
        )
    }
}