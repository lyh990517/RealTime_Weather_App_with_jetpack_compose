package com.rsupport.realtimeweatherapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigation(
    items: List<BottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    Row(
        modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavItem(item, selected) {
                onItemClick(item)
            }
        }
    }
}

@Composable
private fun NavItem(item: BottomNavItem, selected: Boolean, onItemClick: (BottomNavItem) -> Unit) {
    Column(Modifier.clickable {
        onItemClick(item)
    }.padding(10.dp)) {
        if(selected){
            Icon(imageVector = item.icon, contentDescription = item.name, tint = Color.Blue)
        }else{
            Icon(imageVector = item.icon, contentDescription = item.name, tint = Color.Black)
        }
        Text(text = item.name)
    }
}