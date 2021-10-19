/*
 *
 *  * Copyright 2019-2020 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package test.org.springdoc.ui.app21;

import org.junit.jupiter.api.Test;
import org.springdoc.core.Constants;
import test.org.springdoc.ui.AbstractSpringDocTest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SpringDocApp21Test extends AbstractSpringDocTest {

	@Value(Constants.SWAGGER_UI_VERSION)
	private String swaggerUiVersion;


	@Test
	public void testAddSwaggerUiVersionToPath() throws Exception {
		mockMvc.perform(get("/swagger-ui.html"))
				.andExpect(status().isFound())
				.andExpect(header().string("Location", "/swagger-ui/index.html?configUrl=/rest/v3/api-docs/swagger-config"));
	}

	@Test
	public void shouldRedirectWithPrefix() throws Exception {
		mockMvc.perform(get("/rest/v3/api-docs/swagger-config"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("validatorUrl", equalTo("")))
				.andExpect(jsonPath("oauth2RedirectUrl", equalTo("http://localhost/swagger-ui/oauth2-redirect.html")));
	}

    @SpringBootApplication
    static class SpringDocTestApp {
    }

}