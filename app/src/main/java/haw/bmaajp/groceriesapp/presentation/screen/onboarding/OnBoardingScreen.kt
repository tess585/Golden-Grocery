package haw.bmaajp.groceriesapp.presentation.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import haw.bmaajp.groceriesapp.R
import haw.bmaajp.groceriesapp.navigation.graph.Graph
import haw.bmaajp.groceriesapp.ui.theme.*

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
) {
    OnBoarding(
        modifier = modifier.fillMaxSize(),
        onClick = {
            navController.popBackStack()
            navController.navigate(Graph.MAIN)
            onBoardingViewModel.saveOnBoardingState(isCompleted = true)
        }
    )
}

@Composable
fun OnBoarding(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.img_onboarding),
            contentDescription = stringResource(R.string.image_on_boarding),
            contentScale = ContentScale.Crop
        )
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 90.dp),
            color = Color.Transparent
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.welcome_to_store),
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize =49.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = stringResource(R.string.desc_welcome),
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Medium,
                    fontSize =16.sp,
                    color = GrayTextColor,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    onClick = {
                        onClick.invoke()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 68.dp)
                        .padding(start = 16.dp, end = 16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Green),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Text(
                        text = stringResource(R.string.get_started),
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun OnBoardingPreview() {
    OnBoarding(onClick = {})
}