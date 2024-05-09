package haw.bmaajp.groceriesapp.presentation.screen.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import haw.bmaajp.groceriesapp.R
import haw.bmaajp.groceriesapp.presentation.common.card.CategoryCard
import haw.bmaajp.groceriesapp.presentation.component.SearchViewBar
import haw.bmaajp.groceriesapp.ui.theme.*
import haw.bmaajp.groceriesapp.utils.DataDummy

@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier
) {
    Scaffold { paddingValues ->
        Column(
            modifier = modifier.padding(paddingValues)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp),
                text = stringResource(R.string.find_products),
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Black
            )

            SearchViewBar(hint = stringResource(id = R.string.search_category))

            LazyVerticalGrid(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(DataDummy.generateDummyCategories()) { categoryItem ->
                    CategoryCard(categoryItem = categoryItem)
                }
            }
        }
    }
}

@Preview
@Composable
fun ExploreScreenPreview() {
    ExploreScreen()
}