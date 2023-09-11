package com.rsupport.detail

import androidx.activity.compose.BackHandler
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun DetailScreen(navHostController: NavHostController){
    Text(text = "detail")
    BackHandler {
        navHostController.popBackStack()
    }
}