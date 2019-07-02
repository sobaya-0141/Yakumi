# Yakumi
Label RecyclerView

<img src="https://user-images.githubusercontent.com/45986582/60495400-ab7f3900-9ceb-11e9-9e69-964ff6238d83.gif" width="288" height="512" />

# DownLoad
```
implementation 'sobaya.app.yakumi:yakumi:0.1.0'
```

# Introduction
Add Layout Yakumi
```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">
    <androidx.recyclerview.widget.RecyclerView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintBottom_toBottomOf="parent"
            android:id="@+id/mainRecycler"/>
    <sobaya.app.yakumi.Yakumi
            android:id="@+id/mainYakumi"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            app:layout_constraintEnd_toEndOf="@+id/mainRecycler"
            app:layout_constraintTop_toTopOf="@+id/mainRecycler"
            app:layout_constraintBottom_toBottomOf="@+id/mainRecycler"/>
</androidx.constraintlayout.widget.ConstraintLayout>
```

Yakumi#setRecyclerView
```
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    with (mainRecycler) {
        adapter = SampleAdapter()
        layoutManager = LinearLayoutManager(this@MainActivity)
//            layoutManager = GridLayoutManager(this@MainActivity, 2)
        setHasFixedSize(true)

        mainYakumi.setRecyclerView(this)
    }
}
```

RecyclerView.Adapter
implements YakumiAdapter
```
class SampleAdapter : RecyclerView.Adapter<ViewHolder>(), Yakumi.YakumiAdapter {
```

override setYakumiText
'''
override fun setYakumiText(position: Int) = position.toString()
'''

# License
```
 Copyright 2019 sobaya

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
```
