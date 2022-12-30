package com.common.composesample.ui.page

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.common.composesample.ui.theme.color_backGround
import com.common.composesample.widget.CoilImage
import com.common.composesample.widget.CustomTopbar
import com.common.composesample.widget.ExploreImageContainer
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch

/**
 * @Author: Sun
 * @CreateDate: 2022/12/23
 * @Description: java类作用描述
 */
@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PersonDetail(
    navController: NavHostController
){
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    val imageUrl = mutableStateOf<Uri?>(null)
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = { CustomTopbar("个人信息", showAction = false) { navController.popBackStack() } },
        sheetContent = { ChoosePicture{ imageUrl.value = it } },
        sheetPeekHeight = 0.dp
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable {
                        scope.launch {
                            if (scaffoldState.bottomSheetState.isCollapsed) {
                                scaffoldState.bottomSheetState.expand()
                            } else {
                                scaffoldState.bottomSheetState.collapse()
                            }
                        }
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "头像",color = Color.Black, modifier = Modifier.weight(1f))
                ExploreImageContainer(
                    modifier = Modifier.size(40.dp)
                ) {
                    CoilImage(imageUrl.value ?: "https://img2.baidu.com/it/u=1876128925,1748328021&fm=253&fmt=auto&app=138&f=JPEG?w=462&h=403")
                }
                Icon(imageVector = Icons.Default.ArrowForwardIos,tint = MaterialTheme.colors.primary, contentDescription = "",modifier = Modifier.size(22.dp))
            }
            Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(color_backGround))
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "名字",color = Color.Black, modifier = Modifier.weight(1f))
                Text(text = "Sun",color = Color.Black)
                Icon(imageVector = Icons.Default.ArrowForwardIos,tint = MaterialTheme.colors.primary, contentDescription = "",modifier = Modifier.size(22.dp))
            }
            Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(color_backGround))
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "拍一拍",color = Color.Black, modifier = Modifier.weight(1f))
                Text(text = "的背并叫了声欧巴",color = Color.Black)
                Icon(imageVector = Icons.Default.ArrowForwardIos,tint = MaterialTheme.colors.primary, contentDescription = "",modifier = Modifier.size(22.dp))
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun ChoosePicture(
    uriChange: (Uri?)->Unit
){
    val cameraPermissionState = rememberPermissionState(
        Manifest.permission.CAMERA
    )
    var cameraUri = LocalContext.current.contentResolver.insert(
        if(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED){
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }else{
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        },
        ContentValues()
    )
    val openCameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = {
            if(it){ uriChange(cameraUri) }
        }
    )
    val selectPictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            uriChange(it)
        }
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(
            onClick = {
                if (cameraPermissionState.status.isGranted) {
                    openCameraLauncher.launch(cameraUri)
                } else {
                    cameraPermissionState.launchPermissionRequest()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "拍照", color = Color.Black)
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color_backGround))

        TextButton(
            onClick = {
                selectPictureLauncher.launch("image/*")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "图片", color = Color.Black)
        }
    }
}