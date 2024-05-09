package haw.bmaajp.groceriesapp.presentation.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import haw.bmaajp.groceriesapp.R
import haw.bmaajp.groceriesapp.domain.model.ProductItem
import haw.bmaajp.groceriesapp.presentation.common.SpacerDividerContent
import haw.bmaajp.groceriesapp.presentation.component.RatingBar
import haw.bmaajp.groceriesapp.ui.theme.*
import haw.bmaajp.groceriesapp.utils.showToastShort

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = hiltViewModel(),
) {
    val mContext = LocalContext.current
    val selectedProduct by detailViewModel.selectedProduct.collectAsState()

    Scaffold { padding ->
        Column {
            Column(
                modifier = modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f)
                    .padding(padding)
            ) {
                selectedProduct?.let { productItem ->
                    DetailContentImageHeader(productItem = productItem)

                    Spacer(modifier = Modifier.height(24.dp))

                    DetailContentDescription(productItem = productItem)
                }
            }

            Column {
                selectedProduct?.let {
                    DetailButtonAddCart(
                        productItem = it,
                        onClickToCart = { productItem ->
                            mContext.showToastShort("Success Add To Cart ${productItem.title}")
                            detailViewModel.addCart(productItem.copy(isCart = true))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DetailContentImageHeader(
    productItem: ProductItem
) {
    Card(
        shape = RoundedCornerShape(bottomEnd = 24.dp, bottomStart =24.dp),
        backgroundColor = GrayBackground,
        modifier = Modifier
            .blur(1.dp)
            .fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(id = productItem.image),
            contentDescription = stringResource(id = R.string.image_product),
            modifier = Modifier.height(353.dp)
        )
    }
}

@Composable
fun DetailContentDescription(
    modifier: Modifier = Modifier,
    productItem: ProductItem
) {
    Column(
        modifier = modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = productItem.title,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    color = Black,
                    fontSize = 24.sp
                )

                Spacer(modifier = Modifier.height(DIMENS_6dp))

                Text(
                    text = productItem.unit,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Medium,
                    color = GraySecondTextColor,
                    fontSize = 12.sp,
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_favorite_border),
                contentDescription = stringResource(R.string.image_favorite),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.height(DIMENS_8dp))

        Text(
            text = "Ksh.${productItem.price}",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = Black,
            modifier = Modifier.align(Alignment.End),
            fontSize = 18.sp
        )

        SpacerDividerContent()

        Text(
            text = stringResource(R.string.product_detail),
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.SemiBold,
            color = Black,
            fontSize = 16.sp,
        )

        Spacer(modifier = Modifier.height(DIMENS_8dp))

        Text(
            text = productItem.description,
            fontFamily = GilroyFontFamily,
            fontWeight = FontWeight.Medium,
            color = GraySecondTextColor,
            fontSize = TEXT_SIZE_12sp,
        )

        Spacer(modifier = Modifier.height(DIMENS_16dp))
        SpacerDividerContent()

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.nutritions),
                fontFamily = GilroyFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Black,
                fontSize = TEXT_SIZE_16sp,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )

            Card(
                shape = RoundedCornerShape(DIMENS_6dp),
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = productItem.nutritions,
                    fontFamily = GilroyFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = GraySecondTextColor,
                    fontSize = TEXT_SIZE_10sp,
                    modifier = Modifier
                        .background(color = GrayBackgroundSecond)
                        .padding(DIMENS_4dp)
                )
            }

            Spacer(modifier = Modifier.width(DIMENS_8dp))

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = stringResource(id = R.string.arrow_right)
            )
        }

        SpacerDividerContent()

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.review),
                fontFamily = GilroyFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Black,
                fontSize = TEXT_SIZE_16sp,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )

            RatingBar(rating = productItem.review)

            Spacer(modifier = Modifier.width(DIMENS_8dp))

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = stringResource(id = R.string.arrow_right)
            )
        }

    }
}

@Composable
fun DetailButtonAddCart(
    modifier: Modifier = Modifier,
    productItem: ProductItem,
    onClickToCart: (ProductItem) -> Unit
) {
    Button(
        shape = RoundedCornerShape(DIMENS_24dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Green),
        modifier = modifier
            .fillMaxWidth()
            .padding(DIMENS_16dp),
        onClick = { onClickToCart.invoke(productItem) }
    ) {
        Text(
            text = stringResource(R.string.add_to_cart),
            fontFamily = GilroyFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = TEXT_SIZE_18sp,
            color = Color.White,
            modifier = Modifier.padding(top = DIMENS_8dp, bottom = DIMENS_8dp)
        )
    }
}


@Preview
@Composable
fun DetailScreenImageHeaderPreview() {
    DetailContentImageHeader(
        ProductItem(
            id = 1,
            title = "Organic Bananas",
            description = "Apples are nutritious. Apples may be good for weight loss. apples may be good for your heart. As part of a healtful and varied diet.",
            image = R.drawable.product2,
            unit = "7pcs, Priceg",
            price = 4.99,
            nutritions = "100gr",
            review = 4.0
        )
    )
}

@Preview
@Composable
fun DetailContentDescriptionPreview() {
    DetailContentImageHeader(
        ProductItem(
            id = 1,
            title = "Organic Bananas",
            description = "Apples are nutritious. Apples may be good for weight loss. apples may be good for your heart. As part of a healtful and varied diet.",
            image = R.drawable.product2,
            unit = "7pcs, Priceg",
            price = 4.99,
            nutritions = "100gr",
            review = 4.0
        )
    )
}