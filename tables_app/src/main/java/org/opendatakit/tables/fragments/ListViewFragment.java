/*
 * Copyright (C) 2014 University of Washington
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.opendatakit.tables.fragments;

import android.app.Fragment;

/**
 * {@link Fragment} for displaying a List view.
 *
 * @author sudar.sam@gmail.com
 */
public class ListViewFragment extends AbsWebTableFragment {

  /**
   * Used for logging
   */
  @SuppressWarnings("unused")
  private static final String TAG = ListViewFragment.class.getSimpleName();

  public void databaseAvailable() {
    super.databaseAvailable();
    TableAddAccessChecker.check(this);
  }
}
