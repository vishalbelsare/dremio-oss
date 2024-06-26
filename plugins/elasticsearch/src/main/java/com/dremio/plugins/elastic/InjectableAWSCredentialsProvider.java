/*
 * Copyright (C) 2017-2019 Dremio Corporation
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
package com.dremio.plugins.elastic;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import org.glassfish.jersey.spi.Contract;

/**
 * Wrapper class required to inject AWSCredentialsProviders using Jersey. With Jersey HK2, arbitrary
 * interfaces cannot be registered and must be annotated with @Contract.
 */
@Contract
public class InjectableAWSCredentialsProvider implements AWSCredentialsProvider {

  private final AWSCredentialsProvider credentialsProvider;

  public InjectableAWSCredentialsProvider(AWSCredentialsProvider credentialsProvider) {
    this.credentialsProvider = credentialsProvider;
  }

  @Override
  public AWSCredentials getCredentials() {
    return credentialsProvider.getCredentials();
  }

  @Override
  public void refresh() {
    credentialsProvider.refresh();
  }
}
