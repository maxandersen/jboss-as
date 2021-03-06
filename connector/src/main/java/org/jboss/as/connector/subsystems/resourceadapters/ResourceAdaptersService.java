/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.as.connector.subsystems.resourceadapters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jboss.jca.common.api.metadata.resourceadapter.ResourceAdapter;
import org.jboss.jca.common.api.metadata.resourceadapter.ResourceAdapters;
import org.jboss.jca.common.metadata.resourceadapter.ResourceAdaptersImpl;
import org.jboss.logging.Logger;
import org.jboss.msc.service.Service;
import org.jboss.msc.service.StartContext;
import org.jboss.msc.service.StartException;
import org.jboss.msc.service.StopContext;

/**
 * A ResourceAdaptersService.
 * @author <a href="mailto:stefano.maestri@redhat.comdhat.com">Stefano
 *         Maestri</a>
 */
final class ResourceAdaptersService implements Service<ResourceAdaptersService.ModifiableResourceAdapeters> {

    private final ModifiableResourceAdapeters value;

    private static final Logger log = Logger.getLogger("org.jboss.as.resourceadapters");

    /** create an instance **/
    public ResourceAdaptersService(ModifiableResourceAdapeters value) {
        this.value = value;
    }

    @Override
    public ModifiableResourceAdapeters getValue() throws IllegalStateException {
        return value;
    }

    @Override
    public void start(StartContext context) throws StartException {
        log.debugf("Starting ResourceAdapters Service");
    }

    @Override
    public void stop(StopContext context) {

    }

    public static final class ModifiableResourceAdapeters implements ResourceAdapters {

        private static final long serialVersionUID = 9096011997958619051L;
        private final ArrayList<ResourceAdapter> resourceAdapters;

        /**
         * Create a new ResopurceAdaptersImpl.
         * @param resourceAdapters resourceAdapters
         */
        public ModifiableResourceAdapeters(List<ResourceAdapter> resourceAdapters) {
            super();
            if (resourceAdapters != null) {
                this.resourceAdapters = new ArrayList<ResourceAdapter>(resourceAdapters.size());
                this.resourceAdapters.addAll(resourceAdapters);
            } else {
                this.resourceAdapters = new ArrayList<ResourceAdapter>(0);
            }
        }

        /**
         * Get the resourceAdapters.
         * @return the resourceAdapters.
         */
        @Override
        public List<ResourceAdapter> getResourceAdapters() {
            return Collections.unmodifiableList(resourceAdapters);
        }

        public boolean addResourceAdapter(ResourceAdapter ra) {
            return resourceAdapters.add(ra);
        }

        public boolean addAllResourceAdapters(Collection<ResourceAdapter> ras) {
            return resourceAdapters.addAll(ras);
        }

        public boolean removeAllResourceAdapters(Collection<ResourceAdapter> ras) {
            return resourceAdapters.removeAll(ras);
        }

    }

}
