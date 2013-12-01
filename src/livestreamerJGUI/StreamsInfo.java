/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package livestreamerJGUI;

import com.google.gson.Gson;

/**
 *
 * @author Matthias
 */
public class StreamsInfo {
    private Links _links;

    public Links getLinks() {
        return _links;
    }

    public void setLinks(Links _links) {
        this._links = _links;
    }

    public Stream[] getStreams() {
        return streams;
    }

    public void setStreams(Stream[] streams) {
        this.streams = streams;
    }
    private Stream[] streams;
    
    public static class Links {
        private String self;

        public String getSelf() {
            return self;
        }

        public void setSelf(String self) {
            this.self = self;
        }

        public String getFeatured() {
            return featured;
        }

        public void setFeatured(String featured) {
            this.featured = featured;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getFollowed() {
            return followed;
        }

        public void setFollowed(String followed) {
            this.followed = followed;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }
        private String featured;
        private String summary;
        private String followed;
        private String next;
    }
    
    public static class Stream {
        private long _id;

        public long getId() {
            return _id;
        }

        public void setId(long _id) {
            this._id = _id;
        }

        public String getGame() {
            return game;
        }

        public void setGame(String game) {
            this.game = game;
        }

        public int getViewers() {
            return viewers;
        }

        public void setViewers(int viewers) {
            this.viewers = viewers;
        }

        public Preview getPreview() {
            return preview;
        }

        public void setPreview(Preview preview) {
            this.preview = preview;
        }

        public Links getLinks() {
            return links;
        }

        public void setLinks(Links links) {
            this.links = links;
        }

        public Channel getChannel() {
            return channel;
        }

        public void setChannel(Channel channel) {
            this.channel = channel;
        }
        private String game;
        private int viewers;
        private Preview preview;
        private Links links;
        private Channel channel;
        
        public static class Preview {
            private String small;
            private String medium;
            private String large;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getTemplate() {
                return template;
            }

            public void setTemplate(String template) {
                this.template = template;
            }
            private String template;
        }
        
        public static class Link {
            private String self;

            public String getSelf() {
                return self;
            }

            public void setSelf(String self) {
                this.self = self;
            }
        }
        
        public static class Channel {
            private boolean mature;
            private boolean abuse_reported;
            private String status;
            private String display_name;
            private String game;
            private int delay;
            private long _id;
            private String name;
            private String created_at;
            private String updated_at;
            private String logo;
            private String banner;
            private String video_banner;
            private String background;

            public boolean isMature() {
                return mature;
            }

            public void setMature(boolean mature) {
                this.mature = mature;
            }

            public boolean isAbuse_reported() {
                return abuse_reported;
            }

            public void setAbuse_reported(boolean abuse_reported) {
                this.abuse_reported = abuse_reported;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDisplay_name() {
                return display_name;
            }

            public void setDisplay_name(String display_name) {
                this.display_name = display_name;
            }

            public String getGame() {
                return game;
            }

            public void setGame(String game) {
                this.game = game;
            }

            public int getDelay() {
                return delay;
            }

            public void setDelay(int delay) {
                this.delay = delay;
            }

            public long getId() {
                return _id;
            }

            public void setId(long _id) {
                this._id = _id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getBanner() {
                return banner;
            }

            public void setBanner(String banner) {
                this.banner = banner;
            }

            public String getVideo_banner() {
                return video_banner;
            }

            public void setVideo_banner(String video_banner) {
                this.video_banner = video_banner;
            }

            public String getBackground() {
                return background;
            }

            public void setBackground(String background) {
                this.background = background;
            }

            public String getProfile_banner() {
                return profile_banner;
            }

            public void setProfile_banner(String profile_banner) {
                this.profile_banner = profile_banner;
            }

            public String getProfile_banner_background_color() {
                return profile_banner_background_color;
            }

            public void setProfile_banner_background_color(String profile_banner_background_color) {
                this.profile_banner_background_color = profile_banner_background_color;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public long getViews() {
                return views;
            }

            public void setViews(long views) {
                this.views = views;
            }

            public Links getLinks() {
                return links;
            }

            public void setLinks(Links links) {
                this.links = links;
            }
            private String profile_banner;
            private String profile_banner_background_color;
            private String url;
            private long views;
            private Links links;
            
            public static class Links {
                private String self;
                private String follows;
                private String commercial;
                private String stream_key;
                private String chat;
                private String features;

                public String getSelf() {
                    return self;
                }

                public void setSelf(String self) {
                    this.self = self;
                }

                public String getFollows() {
                    return follows;
                }

                public void setFollows(String follows) {
                    this.follows = follows;
                }

                public String getCommercial() {
                    return commercial;
                }

                public void setCommercial(String commercial) {
                    this.commercial = commercial;
                }

                public String getStream_key() {
                    return stream_key;
                }

                public void setStream_key(String stream_key) {
                    this.stream_key = stream_key;
                }

                public String getChat() {
                    return chat;
                }

                public void setChat(String chat) {
                    this.chat = chat;
                }

                public String getFeatures() {
                    return features;
                }

                public void setFeatures(String features) {
                    this.features = features;
                }

                public String getSubscriptions() {
                    return subscriptions;
                }

                public void setSubscriptions(String subscriptions) {
                    this.subscriptions = subscriptions;
                }

                public String getEditors() {
                    return editors;
                }

                public void setEditors(String editors) {
                    this.editors = editors;
                }

                public String getTeams() {
                    return teams;
                }

                public void setTeams(String teams) {
                    this.teams = teams;
                }

                public String getVideos() {
                    return videos;
                }

                public void setVideos(String videos) {
                    this.videos = videos;
                }
                private String subscriptions;
                private String editors;
                private String teams;
                private String videos;
            }
        }
    }
    
    public static StreamsInfo fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, StreamsInfo.class);
    }
}
