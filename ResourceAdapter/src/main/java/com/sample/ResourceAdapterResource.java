/**
* Copyright 2016 IBM Corp.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.sample;

import com.ibm.mfp.adapter.api.OAuthSecurity;
import com.ibm.mfp.server.registration.external.model.AuthenticatedUser;
import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Produces;

@Path("/")
public class ResourceAdapterResource {

	@Context
	AdapterSecurityContext securityContext;

	/* Path for method: "<server address>/mfp/api/adapters/ResourceAdapter" */
	@PATCH
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/balance")
	@OAuthSecurity(scope="accessRestricted") //This method is protected. Each application can define what "accessRestricted" means.
	public String getBalanceNew(){
		return "PATCH Method Balance 19938.80";
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/balance")
	@OAuthSecurity(scope="accessRestricted") //This method is protected. Each application can define what "accessRestricted" means.
	public String getBalance(){
		return "19938.80";
	}

	@POST
	@Path("/transfer")
	@OAuthSecurity(scope="transferPrivilege") //This method is protected. Each application can define what "transferPrivilege" means.
	public Response transfer(@FormParam("amount") float amount){
		return Response.ok().build();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/publicData")
	@OAuthSecurity(enabled=false)
	public String getPublicData(){
		return "Lorem ipsum dolor sit amet, modo oratio cu nam, mei graece dicunt tamquam ne.";
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/transactions")
	@OAuthSecurity(scope = "transactions")
	public String getTransactions(){
		AuthenticatedUser currentUser = securityContext.getAuthenticatedUser();
		return "Transactions for " + currentUser.getDisplayName() + ":\n{'date':'12/01/2016', 'amount':'19938.80'}";
	}
}
