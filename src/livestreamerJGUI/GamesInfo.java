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
public class GamesInfo {

    private int _total;
    private Links _links;
    private Top[] top;

    public int getTotal() {
        return _total;
    }

    public void setTotal(int _total) {
        this._total = _total;
    }

    public Links getLinks() {
        return _links;
    }

    public void setLinks(Links _links) {
        this._links = _links;
    }

    public Top[] getTop() {
        return top;
    }

    public void setTop(Top[] top) {
        this.top = top;
    }

    public static class Links {

        private String self;

        public String getSelf() {
            return self;
        }

        public void setSelf(String self) {
            this.self = self;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }
        private String next;
    }

    public static class Top {

        private int channels;
        private int viewers;
        private Game game;

        public int getChannels() {
            return channels;
        }

        public void setChannels(int channels) {
            this.channels = channels;
        }

        public Game getGame() {
            return game;
        }

        public void setGame(Game game) {
            this.game = game;
        }

        public int getViewers() {
            return viewers;
        }

        public void setViewers(int viewers) {
            this.viewers = viewers;
        }

        public static class Game {

            private String name;
            private long _id;
            private long giantbomb_id;
            private Box box;
            private Logo logo;

            public long getGiantbomb_id() {
                return giantbomb_id;
            }

            public void setGiantbomb_id(long giantbomb_id) {
                this.giantbomb_id = giantbomb_id;
            }

            public Box getBox() {
                return box;
            }

            public void setBox(Box box) {
                this.box = box;
            }

            public Logo getLogo() {
                return logo;
            }

            public void setLogo(Logo logo) {
                this.logo = logo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getId() {
                return _id;
            }

            public void setId(long _id) {
                this._id = _id;
            }

            public static class Box {

                public String getTemplate() {
                    return template;
                }

                public void setTemplate(String template) {
                    this.template = template;
                }

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

                String template;
                String small;
                String medium;
                String large;
            }

            public static class Logo {

                public String getTemplate() {
                    return template;
                }

                public void setTemplate(String template) {
                    this.template = template;
                }

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

                String template;
                String small;
                String medium;
                String large;
            }
        }
    }

    public static GamesInfo fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, GamesInfo.class);
    }
}
