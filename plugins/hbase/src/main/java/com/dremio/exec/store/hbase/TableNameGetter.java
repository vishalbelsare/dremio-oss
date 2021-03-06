/*
 * Copyright (C) 2017-2018 Dremio Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dremio.exec.store.hbase;

import java.util.List;

import org.apache.hadoop.hbase.TableName;

import com.dremio.service.namespace.NamespaceKey;

/**
 * Retrieve HBase table namespace/name from Dremio namespace key.
 */
public final class TableNameGetter {

  private TableNameGetter() {}

  public static TableName getTableName(NamespaceKey key) {
    switch(key.size()) {
    case 2:
      return TableName.valueOf(key.getName());

    case 3:
      final List<String> paths = key.getPathComponents();
      return TableName.valueOf(paths.get(1), paths.get(2));

    default:
      throw new IllegalStateException("Unexpected key lenth: " + key);
    }
  }
}
