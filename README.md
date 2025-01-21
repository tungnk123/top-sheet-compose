# Top Sheet Compose Library

A library for creating top sheets in Jetpack Compose.

## Demo

https://github.com/user-attachments/assets/f522f58f-2b17-4591-9141-1b6c48475252

## Features

- Simple and customizable API for top sheet components.
- Fully compatible with Jetpack Compose.
- Smooth animations and easy integration.

---

## Getting Started

To use this library in your project, follow these steps:

### Step 1: Add the JitPack Repository

Add the JitPack repository to your project's `settings.gradle` file:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2: Add the Dependency

Add the following dependency to your module's `build.gradle` file:

```groovy
dependencies {
    implementation 'com.github.tungnk123:top-sheet-compose:1.0'
}
```

---

## Usage

Here's an example of how to use the `TopSheet` component:

### Basic Example

```kotlin
val topSheetState = rememberTopSheetState()

scope.launch {
    topSheetState.expand()
    topSheetState.collapse()
}

TopSheet(
    modifier = modifier,
    sheetHeight = 300.dp,
    topSheetState = topSheetState,
    onDismissRequest = {},
    content = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {
            Text("This is a simple top sheet", Modifier.padding(16.dp))
        }
    },
)
```

### Customization

You can customize the `TopSheet` properties such as:

- **`modifier`**: Customize the outer layout of the `TopSheet`.
- **`sheetHeight`**: Maximum height of the top sheet.
- **`sheetMaxWidth`**: Maximum width of the top sheet.
- **`topSheetState`**: State of the top sheet (`Expanded`, `Collapsed`).
- **`shape`**: Shape of the top sheet (rounded corners, rectangle, etc.).
- **`containerColor`**: Background color of the top sheet.
- **`contentColor`**: Content color inside the top sheet.
- **`scrimColor`**: Dimmed overlay color behind the top sheet.
- **`tonalElevation`**: Elevation for the top sheet's shadow.
- **`windowInsets`**: Insets of the sheet relative to the screen edges.
- **`dragHandle`**: A composable to display the drag handle.
- **`isDragHandleVisible`**: Whether the drag handle is visible.
- **`properties`**: Configuration options, such as dismiss behavior.
- **`onDismissRequest`**: Callback invoked when the sheet should be dismissed.

Example with customization:

```kotlin
val topSheetState = rememberTopSheetState()

TopSheet(
    modifier = modifier,
    sheetHeight = 500.dp,
    topSheetState = topSheetState,
    containerColor = Color.Black,
    contentColor = Color.Blue,
    scrimColor = Color.Black.copy(alpha = 0.7f),
    tonalElevation = 20.dp,
    isDragHandleVisible = true,
    onDismissRequest = {},
    dragHandle = {
        Box(
            modifier = Modifier
                .background(Color.Red, shape = RoundedCornerShape(20.dp))
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_drag),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    },
    content = {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
        ) {
            Text("This is a advanced top sheet", Modifier.padding(16.dp))
        }
    },
)
```
## TopSheetState Operations
The TopSheetState class provides the following operations:

- `currentValue`: Retrieves the current state (Expanded or Collapsed).
- `expand()`: Expands the top sheet.
- `collapse()`: Collapses the top sheet.
- `animateTo(targetValue: SheetState)`: Animates the top sheet to a target state.
- `confirmStateChange`: Callback to validate state changes before they occur.
---

## Note on Using `TopSheet` with `Scaffold`

When using the `TopSheet` component in a layout with a `Scaffold`, **ensure that the `TopSheet` is placed outside the `Scaffold`'s content hierarchy.** This is because `Scaffold` manages its own internal layout and can interfere with the behavior and positioning of the `TopSheet`.

For a detailed example, refer to [this example on GitHub](https://github.com/tungnk123/top-sheet-compose/blob/develop/app/src/main/java/com/tungnk123/topsheetcompose/examples/ScaffoldWithTopSheetExample.kt).

---

## Contributions

Contributions are welcome! If you find any bugs or have feature requests, please open an issue or submit a pull request on [GitHub](https://github.com/tungnk123/top-sheet-compose).
