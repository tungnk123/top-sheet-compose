# Top Sheet Compose Library

A library for creating top sheets in Jetpack Compose.

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

### Customization

You can customize the `TopSheet` properties such as:

- **`shape`**: Customize the shape of the top sheet.
- **`containerColor`**: Set the background color.
- **`scrimColor`**: Adjust the overlay color.
- **`sheetHeight`**: Specify the maximum height of the sheet.

Example with customization:

```kotlin
TopSheet(
    onDismissRequest = { isTopSheetVisible = false },
    sheetHeight = 300.dp,
    containerColor = Color.White,
    scrimColor = Color.Black.copy(alpha = 0.5f),
    content = {
        Text("Customized Top Sheet Content")
    }
)
```

---

## Contributions

Contributions are welcome! If you find any bugs or have feature requests, please open an issue or submit a pull request on [GitHub](https://github.com/tungnk123/top-sheet-compose).