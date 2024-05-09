package haw.bmaajp.groceriesapp.presentation.screen.cart

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import haw.bmaajp.groceriesapp.R
import haw.bmaajp.groceriesapp.presentation.common.content.ListContentCart
import haw.bmaajp.groceriesapp.presentation.screen.explore.ExploreScreen
import haw.bmaajp.groceriesapp.ui.theme.Black
import haw.bmaajp.groceriesapp.ui.theme.DIMENS_16dp
import haw.bmaajp.groceriesapp.ui.theme.GilroyFontFamily
import haw.bmaajp.groceriesapp.ui.theme.Green
import haw.bmaajp.groceriesapp.ui.theme.TEXT_SIZE_18sp

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val productCartList by cartViewModel.productCartList.collectAsState()

    Column(
        modifier = modifier
            .background(Green)
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = DIMENS_16dp),
            text = stringResource(R.string.my_cart),
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(DIMENS_16dp))

        ListContentCart(
            cartProducts = productCartList,
            onClickDeleteCart = { productItem ->
                cartViewModel.deleteCart(productItem.copy(isCart = false))

            }
        )
    }
}
@Preview
@Composable
fun CartScreenPreview() {
    CartScreen()
}